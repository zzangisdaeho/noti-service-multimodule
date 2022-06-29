package com.example.notiservice.messaging;

import com.example.notiservice.domain.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendResultMessage(String message){
      log.info("message : {}, send with topic {}", message, TopicConstList.NOTIFICATION_SEND_RESULT);
      kafkaTemplate.send(TopicConstList.NOTIFICATION_SEND_RESULT, message);
    }

    public void sendStartMessage(String notification){
        log.info("message : {}, send with topic {}", notification, TopicConstList.NOTIFICATION_SEND_CMD);
        kafkaTemplate.send(TopicConstList.NOTIFICATION_SEND_CMD, notification);
    }

    public void sendRetryMessage(String channel){
        log.warn("retry message : {}, send with topic {}", channel, TopicConstList.NOTIFICATION_SEND_RETRY);
        kafkaTemplate.send(TopicConstList.NOTIFICATION_SEND_RETRY, channel);
//        Message<String> message = MessageBuilder
//                .withPayload(channel)
//                .setHeader(KafkaHeaders.RETRY_COUNT, retryCount)
//                .build();
//
//        kafkaTemplate.send(TopicConstList.NOTIFICATION_SEND_RETRY, message);
    }
}
