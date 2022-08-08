package com.example.notimessagesconsumer.message;

import com.example.noticore.domain.request_all.Notification;
import com.example.noticore.domain.request_each.NotificationEach;
import com.example.noticore.message.TopicConstList;
import com.example.notimessagesconsumer.util.JsonDeserializer;
import com.example.notimessagesconsumer.util.JsonSerializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.WindowStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class NotificationAggregator {

    private final NotificationResultProducer notificationResultProducer;

    private ExpiringMap<String, Tuple> resultMap;

    public NotificationAggregator(NotificationResultProducer notificationResultProducer) {
        this.notificationResultProducer = notificationResultProducer;
        this.resultMap = ExpiringMap.builder()
                .maxSize(Integer.MAX_VALUE)
                .expiration(30, TimeUnit.SECONDS)
                .expirationPolicy(ExpirationPolicy.CREATED)
                .asyncExpirationListener((key, tuple) -> {
                    String stringKey = (String) key;
                    Tuple tupleValue = (Tuple) tuple;
                    String result = tupleValue.isFinish() ? "true" : "false";
                    notificationResultProducer.sendFinalResultMessage(stringKey, result);
                })
                .build();
    }


    @KafkaListener(topics = TopicConstList.NOTIFICATION_SEND_RESULT)
    public void aggregator(ConsumerRecord<String,String> record){

        NotificationEach unmarshalling = unmarshalling(record.value());

        if(resultMap.containsKey(record.key())){
            Tuple tuple = this.resultMap.get(record.key());
            tuple.increaseMount();
            if(tuple.isFinish()){
                notificationResultProducer.sendFinalResultMessage(record.key(), "true");
                resultMap.remove(record.key());
            }
        }else{
            resultMap.put(record.key(), new Tuple(1, unmarshalling.getTotal()));
        }
    }

    private NotificationEach unmarshalling(String request) {
        ObjectMapper objectMapper = new ObjectMapper();
        NotificationEach notificationEach = null;
        try {
            notificationEach = objectMapper.readValue(request, NotificationEach.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return notificationEach;
    }



    long joinWindowSizeMs = 5L * 60L * 1000L; // 5 minutes
    long windowRetentionTimeMs = 1L * 24L * 60L * 60L * 1000L; // 1 days

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {

        KStream<String, String> successStream = streamsBuilder.stream(TopicConstList.NOTIFICATION_SEND_RESULT);

//        KTable<String, String> table = streamsBuilder.table(TopicConstList.NOTIFICATION_FAIL_RESULT);
//        KStream<String, String> failStream = streamsBuilder.stream(TopicConstList.NOTIFICATION_FAIL_RESULT);

//        successStream.groupByKey()
//                .windowedBy(TimeWindows.ofSizeWithNoGrace(Duration.ofSeconds(30)))
//                .count(Materialized.with(Serdes.String(), Serdes.Long()))
//                .suppress(Suppressed.untilWindowCloses(Suppressed.BufferConfig.unbounded()))
//                .toStream()
//                .peek((key, value) -> {
//                    log.info(key.key() + " is [" + key.window().startTime() + "~" + key.window().endTime() + "] count : " + value);
//                });

        JsonSerializer<LogCount> logCountJsonSerializer = new JsonSerializer<>();
        JsonDeserializer<LogCount> logCountJsonDeserializer = new JsonDeserializer<>(LogCount.class);
        Serde<LogCount> logCountJsonSerde = Serdes.serdeFrom(logCountJsonSerializer, logCountJsonDeserializer);

        successStream.selectKey(((k, v) -> getKey(v)))
                .groupByKey()
                .windowedBy(TimeWindows.ofSizeAndGrace(Duration.ofSeconds(30), Duration.ofSeconds(5)))
                .aggregate(() -> new LogCount(),
                        (key, value, aggregate) -> aggregate.add(value),
                        Materialized.with(Serdes.String(), logCountJsonSerde)
                )
                .suppress(Suppressed.untilWindowCloses(Suppressed.BufferConfig.unbounded()))
                .toStream()
                .peek((key, value) -> {
                    log.info(key.key() + " is [" + key.window().startTime() + "~" + key.window().endTime() + "] count : " + value);
                });


//        count.toStream().foreach(((key, value) -> {
//            log.info(key.key() + " is [" + key.window().startTime() + "~" + key.window().endTime() + "] count : " + value);
//        }));
    }

        private String getKey(String value) {
            ObjectMapper objectMapper = new ObjectMapper();
            NotificationEach notification = null;
            try {
                notification = objectMapper.readValue(value, NotificationEach.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            log.info("transaction Id = {} , index = {}, total = {}", notification.getTransactionId(), notification.getIndex(), notification.getTotal());
            return notification.getTransactionId();
        }



}
