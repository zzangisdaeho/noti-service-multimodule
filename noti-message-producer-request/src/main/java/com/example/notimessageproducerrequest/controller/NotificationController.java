package com.example.notimessageproducerrequest.controller;

import com.example.noticore.domain.Notification;
import com.example.notimessageproducerrequest.messaging.NotificationProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationProducer notificationProducer;

    @PostMapping("/notification")
    public void sendNotification(@RequestBody Notification request){

        notificationProducer.sendStartMessage(request);
    }
}
