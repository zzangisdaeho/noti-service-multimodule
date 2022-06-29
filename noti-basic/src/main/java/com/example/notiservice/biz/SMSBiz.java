package com.example.notiservice.biz;

import com.example.notiservice.db.nosql.document.NotificationLog;
import com.example.notiservice.db.nosql.repository.NotificationLogRepository;
import com.example.notiservice.db.sql.entity.SystemType;
import com.example.notiservice.domain.channel.NotificationChannel;
import com.example.notiservice.domain.channel.SMSNotification;
import com.example.notiservice.domain.channel.Status;
import com.example.notiservice.service.CredentialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class SMSBiz implements ThirdPartyInterface{

    private final CredentialService credentialService;

    @Override
    public boolean support(Object channel) {
        return channel instanceof SMSNotification;
    }

    @Override
    @Async
    public CompletableFuture<NotificationChannel> send(Object channel, String title, String content) {
        SMSNotification smsNotification = (SMSNotification) channel;
        String credential = credentialService.getCredential(SystemType.SMS);
        log.info("sending SMS... from {}, to {}, title: {}, content: {}, credential: {}", smsNotification.getSenderPhoneNumber(), smsNotification.getReceiverPhoneNumber(), title, content, credential);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException("에러 발생했으욥");
//        log.info("send finish from {}, to {}, title: {}, content: {}, credential: {}", smsNotification.getSenderPhoneNumber(), smsNotification.getReceiverPhoneNumber(), title, content, credential);
//
//        smsNotification.setIsSuccess(Status.SUCCESS);
//        return CompletableFuture.completedFuture(smsNotification);
    }
}
