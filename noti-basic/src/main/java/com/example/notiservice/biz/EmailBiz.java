package com.example.notiservice.biz;

import com.example.notiservice.db.nosql.document.NotificationLog;
import com.example.notiservice.db.nosql.repository.NotificationLogRepository;
import com.example.notiservice.db.sql.entity.SystemType;
import com.example.notiservice.domain.channel.Status;
import com.example.notiservice.domain.channel.EmailNotification;
import com.example.notiservice.domain.channel.NotificationChannel;
import com.example.notiservice.service.CredentialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailBiz implements ThirdPartyInterface{

    private final CredentialService credentialService;

    @Override
    public boolean support(Object channel) {
        return channel instanceof EmailNotification;
    }

    @Override
    @Async
    public CompletableFuture<NotificationChannel> send(Object channel, String title, String content) {
        EmailNotification emailNotification = (EmailNotification) channel;
        String credential = credentialService.getCredential(SystemType.EMAIL);
        log.info("sending email... from {}, to {}, title: {}, content: {}, credential: {}", emailNotification.getSenderEmailAddress(), emailNotification.getReceiverEmailAddress(), title, content, credential);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("send finish from {}, to {}, title: {}, content: {}, credential: {}", emailNotification.getSenderEmailAddress(), emailNotification.getReceiverEmailAddress(), title, content, credential);

        emailNotification.setIsSuccess(Status.SUCCESS);

        return CompletableFuture.completedFuture(emailNotification);
    }
}
