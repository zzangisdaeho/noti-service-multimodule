package com.example.notiservice.messaging;

import com.example.notiservice.domain.Notification;
import com.example.notiservice.domain.channel.NotificationChannel;
import com.example.notiservice.event.FailEventDto;
import com.example.notiservice.event.RetryEventDto;
import com.example.notiservice.service.NotificationService;
import com.example.notiservice.util.ValidationUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.LinkedHashMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final ObjectMapper objectMapper;

    private final NotificationService notificationService;

    private final ApplicationEventPublisher applicationEventPublisher;

    @KafkaListener(topics = TopicConstList.NOTIFICATION_SEND_CMD)
    public void consumeNotificationCmd(String arrive) {
        log.info("receive message : {}", arrive);

        //check validation before process
        try {
            Notification notification = objectMapper.readValue(arrive, Notification.class);
            ValidationUtil.checkValidation(notification);

            notificationService.sendProcess(notification);
        } catch (JsonProcessingException e) {
            log.error("json String converting error", e);
            applicationEventPublisher.publishEvent(new FailEventDto("json String converting error", e));
        } catch (ConstraintViolationException e) {
            log.error("request validation fail", e);
            applicationEventPublisher.publishEvent(new FailEventDto("request validation fail", e));
        }

    }

    @KafkaListener(topics = TopicConstList.NOTIFICATION_SEND_RETRY)
    public void retryNotificationCmd(@Payload String retryMessage
//            , @Header(KafkaHeaders.RETRY_COUNT) int retry
    ) {

        try {
            HashMap<String, Object> map = objectMapper.readValue(retryMessage, new TypeReference<HashMap<String, Object>>() {});
            String title = (String) map.get("title");
            String content = (String) map.get("content");
            String senderId = (String) map.get("senderId");
            int retry = (int) map.get("retryCount");

            RetryEventDto retryEventDto = objectMapper.readValue(retryMessage, RetryEventDto.class);
            NotificationChannel notificationChannel = retryEventDto.getChannel();
            log.warn("retry target : {}, try count : {}", notificationChannel, retry);
            ValidationUtil.checkValidation(notificationChannel);

            notificationService.retryProcess(notificationChannel, title, content, senderId, retry);
        } catch (JsonProcessingException e) {
            log.error("json String converting error", e);
            applicationEventPublisher.publishEvent(new FailEventDto("json String converting error", e));
        } catch (ConstraintViolationException e) {
            log.error("request validation fail", e);
            applicationEventPublisher.publishEvent(new FailEventDto("request validation fail", e));
        }



    }

    // 임시. noti result 확인용
    @KafkaListener(topics = TopicConstList.NOTIFICATION_SEND_RESULT)
    public void consumeNotificationResult(String arrive) {
        log.info("result message : {}", arrive);
    }
}
