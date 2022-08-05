package com.example.notimessagesconsumer.message;

import com.example.noticore.message.KafkaHeaders;
import com.example.noticore.message.TopicConstList;
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
public class NotificationFailProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendFailMessage(ConsumerRecord<String, String> consumerRecord) {

        ProducerRecord<String, String> producerRecord = MessageConverter.convertConsumerRecordToProducerRecord(consumerRecord, TopicConstList.NOTIFICATION_FAIL_RESULT);
        kafkaTemplate.send(producerRecord);
    }
}
