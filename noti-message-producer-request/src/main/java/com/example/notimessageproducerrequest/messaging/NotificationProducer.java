package com.example.notimessageproducerrequest.messaging;

import com.example.noticore.message.KafkaHeaders;
import com.example.noticore.message.TopicConstList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {

    private final ObjectMapper objectMapper;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendStartMessage(Object request) {

        String jsonInString = makeJsonString(request);

        ProducerRecord<String, String> record = new ProducerRecord<>(TopicConstList.NOTIFICATION_SEND_CMD, jsonInString);

        record.headers().add(new RecordHeader(KafkaHeaders.RETRY_COUNT, "1".getBytes(StandardCharsets.UTF_8)));

        kafkaTemplate.send(record);
    }

    private String makeJsonString(Object request) {
        String jsonInString = null;
        try{
            jsonInString = objectMapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            log.error("json String parsing error");
            e.printStackTrace();
        }
        return jsonInString;
    }
}
