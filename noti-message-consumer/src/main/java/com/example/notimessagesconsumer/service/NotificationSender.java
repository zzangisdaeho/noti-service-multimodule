package com.example.notimessagesconsumer.service;

import com.example.noticore.domain.request_all.channel.NotificationChannel;
import com.example.noticore.domain.request_each.NotificationEach;
import com.example.notimessagesconsumer.biz.ThirdPartyInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class NotificationSender {

    private final List<ThirdPartyInterface> thirdPartyServices;

    public void send(ConsumerRecord<String, String> record) {

        ObjectMapper objectMapper = new ObjectMapper();

        String request = record.value();

        NotificationEach notificationEach = unmarshalling(objectMapper, request);

        CompletableFuture<NotificationChannel> sendFuture = choiceAdaptor(notificationEach.getNotificationChannel())
                .send(notificationEach.getNotificationChannel(), notificationEach.getTitle(), notificationEach.getContent());
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
