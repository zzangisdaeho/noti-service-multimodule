package com.example.notimessagesconsumer.util;

import com.example.noticore.message.TopicConstList;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;

public class MessageConverter {

    public static <T, R> ProducerRecord<T, R> convertConsumerRecordToProducerRecord(ConsumerRecord<T, R> consumerRecord) {
        return new ProducerRecord<>(consumerRecord.topic(), consumerRecord.key(), consumerRecord.value());
    }

    public static <T, R> ProducerRecord<T, R> convertConsumerRecordToProducerRecord(ConsumerRecord<T, R> consumerRecord, String topic) {
        return new ProducerRecord<>(topic, consumerRecord.key(), consumerRecord.value());
    }
}
