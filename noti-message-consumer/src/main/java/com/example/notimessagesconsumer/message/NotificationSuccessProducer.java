package com.example.notimessagesconsumer.message;

import com.example.noticore.message.TopicConstList;
import com.example.notimessagesconsumer.util.MessageConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationSuccessProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendSuccessMessage(ConsumerRecord<String, String> consumerRecord) {
        ProducerRecord<String, String> producerRecord = MessageConverter.convertConsumerRecordToProducerRecord(consumerRecord, TopicConstList.NOTIFICATION_SEND_RESULT);
        kafkaTemplate.send(producerRecord);
    }
}
