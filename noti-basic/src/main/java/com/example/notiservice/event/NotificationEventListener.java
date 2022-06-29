package com.example.notiservice.event;

import com.example.notiservice.db.nosql.document.NotificationLog;
import com.example.notiservice.messaging.KafkaProducerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationEventListener {

    private final KafkaProducerService kafkaProducerService;
    private final ObjectMapper objectMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @EventListener
    public void sendSuccessEvent(SuccessEventDto successEventDto) {
        log.info("Success Event Listener active for SuccessEventDto : {}", successEventDto);
        kafkaProducerService.sendResultMessage(successEventDto.toString());
    }

    @EventListener
    public void sendFailEvent(FailEventDto failEventDto) {
        log.info("Fail Event Listener active for FailEventDto : {}", failEventDto);

        kafkaProducerService.sendResultMessage(failEventDto.toString());
    }

    @Async
    @EventListener
    public void sendRetryEvent(RetryEventDto retryEventDto) {
        log.info("Retry Event Listener active for RetryEventDto : {}", retryEventDto);

        if (retryEventDto.getRetryCount() > 3) {
            applicationEventPublisher.publishEvent(new FailEventDto("retry 3 times but fail...", new IllegalStateException("retry 3 times. but fail")));
        } else {
            try {
                retryEventDto.setRetryCount(retryEventDto.getRetryCount()+1);
                String retryEvent = objectMapper.writeValueAsString(retryEventDto);
                kafkaProducerService.sendRetryMessage(retryEvent);
            } catch (JsonProcessingException e) {
                log.error("json String converting error", e);
                applicationEventPublisher.publishEvent(new FailEventDto("json String converting error", e));
            }
        }

    }
}
