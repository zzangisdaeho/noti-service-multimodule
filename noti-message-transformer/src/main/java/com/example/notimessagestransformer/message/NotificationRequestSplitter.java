package com.example.notimessagestransformer.message;

import com.example.noticore.domain.request_all.Notification;
import com.example.noticore.domain.request_all.channel.NotificationChannel;
import com.example.noticore.domain.request_each.NotificationEach;
import com.example.noticore.message.TopicConstList;
import com.example.notimessagestransformer.util.ValidationUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.ValidationUtils;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationRequestSplitter {

//    private static final Serde<String> STRING_SERDE = Serdes.String();

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        KStream<String, String> messageStream = streamsBuilder
                .stream(TopicConstList.NOTIFICATION_SEND_CMD);

        AtomicReference<String> uuid = new AtomicReference<>();

        messageStream.mapValues((key, value) -> unmarshalling(value))
                .filter((key, value) -> {
                    boolean validationResult = ValidationUtil.checkValidation(value);
                    if(!validationResult){
                        //todo : 유효하지 못한 요청에 대한 메세지 발행
                    }
                    return validationResult;
                })
                .flatMapValues((value) -> {
                    uuid.set(UUID.randomUUID().toString());
                    List<NotificationChannel> notificationChannels = value.getNotificationChannels();
                    AtomicInteger index = new AtomicInteger(1);
                    return notificationChannels.stream()
                            .map(notificationChannel -> new NotificationEach(uuid.get(), notificationChannel, value.getTitle(), value.getContent(), index.getAndIncrement(), notificationChannels.size()))
                            .collect(Collectors.toList());
                })
                .map((key, value) -> new KeyValue<>(uuid.get(), makeJsonString(value)))
                .to(TopicConstList.NOTIFICATION_SEND_SPLIT);
    }

    private Notification unmarshalling(String value) {
        ObjectMapper objectMapper = new ObjectMapper();
        Notification notification = null;
        try {
            notification = objectMapper.readValue(value, Notification.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return notification;
    }

    private String makeJsonString(Object request) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonInString = null;
        try {
            jsonInString = objectMapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            log.error("json String parsing error");
            e.printStackTrace();
        }
        return jsonInString;
    }
}
