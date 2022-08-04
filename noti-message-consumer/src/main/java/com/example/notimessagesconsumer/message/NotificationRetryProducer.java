package com.example.notimessagesconsumer.message;

import com.example.noticore.message.KafkaHeaders;
import com.example.notimessagesconsumer.util.MessageConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationRetryProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendRetryMessage(ConsumerRecord<String, String> consumerRecord) {

        byte[] value = consumerRecord.headers().lastHeader(KafkaHeaders.RETRY_COUNT).value();
        int retryCount = Integer.parseInt(new String(value)) + 1;
        log.info("retry count : {}" , retryCount-1);

        ProducerRecord<String, String> producerRecord = MessageConverter.convertConsumerRecordToProducerRecord(consumerRecord);
        producerRecord.headers().add(KafkaHeaders.RETRY_COUNT, String.valueOf(retryCount).getBytes(StandardCharsets.UTF_8));
        kafkaTemplate.send(producerRecord);
    }


}
