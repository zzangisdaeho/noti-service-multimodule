package com.example.notimessagesconsumer.message;

import com.example.noticore.message.TopicConstList;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationResultProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendFinalResultMessage(String key, String result) {

        ProducerRecord<String, String> record = new ProducerRecord<>(TopicConstList.NOTIFICATION_FINAL_RESULT, key, result);

        kafkaTemplate.send(record);
    }
}
