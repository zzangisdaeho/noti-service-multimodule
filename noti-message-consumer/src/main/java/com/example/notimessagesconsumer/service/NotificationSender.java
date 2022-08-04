package com.example.notimessagesconsumer.service;

import com.example.noticore.domain.request_all.channel.NotificationChannel;
import com.example.noticore.domain.request_each.NotificationEach;
import com.example.notimessagesconsumer.biz.ThirdPartyInterface;
import com.example.notimessagesconsumer.message.NotificationRetryProducer;
import com.example.notimessagesconsumer.message.NotificationSuccessProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationSender {

    private final List<ThirdPartyInterface> thirdPartyServices;
    private final NotificationRetryProducer notificationRetryProducer;
    private final NotificationSuccessProducer notificationSuccessProducer;

    public void send(ConsumerRecord<String, String> record) {

        ObjectMapper objectMapper = new ObjectMapper();

        NotificationEach notificationEach = unmarshalling(objectMapper, record.value());

        try {
            CompletableFuture<NotificationChannel> sendFuture = choiceAdaptor(notificationEach.getNotificationChannel())
                    .send(notificationEach.getNotificationChannel(), notificationEach.getTitle(), notificationEach.getContent());
            sendFuture.get();
            log.info("send success. produce success message");
            notificationSuccessProducer.sendSuccessMessage(record);
        } catch (Exception e) {
            log.error("send fail. produce retry message");
            notificationRetryProducer.sendRetryMessage(record);
        }

//        try {
//            sendFuture.get();
//            log.info("send success. produce success message");
//            notificationSuccessProducer.sendSuccessMessage(record);
//        } catch (Exception e) {
//            log.error("send fail. produce retry message");
//            notificationRetryProducer.sendRetryMessage(record);
//        }

    }

    private NotificationEach unmarshalling(ObjectMapper objectMapper, String request) {
        NotificationEach notificationEach = null;
        try {
            notificationEach = objectMapper.readValue(request, NotificationEach.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return notificationEach;
    }

    private ThirdPartyInterface choiceAdaptor(NotificationChannel notificationChannel) {
        return thirdPartyServices.stream()
                .filter(thirdPartyService -> thirdPartyService.support(notificationChannel))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("adaptor not found"));
    }
}
