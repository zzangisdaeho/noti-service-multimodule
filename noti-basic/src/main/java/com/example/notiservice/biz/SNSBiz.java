package com.example.notiservice.biz;

import com.example.notiservice.db.nosql.document.NotificationLog;
import com.example.notiservice.db.nosql.repository.NotificationLogRepository;
import com.example.notiservice.db.sql.entity.SystemType;
import com.example.notiservice.domain.channel.Status;
import com.example.notiservice.domain.channel.NotificationChannel;
import com.example.notiservice.domain.channel.SocialNetworkNotification;
import com.example.notiservice.service.CredentialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class SNSBiz implements ThirdPartyInterface{

    private final CredentialService credentialService;

    @Override
    public boolean support(Object channel) {
        return channel instanceof SocialNetworkNotification;
    }

    @Override
    @Async
    public CompletableFuture<NotificationChannel> send(Object channel, String title, String content) {
        SocialNetworkNotification socialNetworkNotification = (SocialNetworkNotification) channel;
        String credential = credentialService.getCredential(SystemType.SNS);
        log.info("sending SNS... from {}, to {}, title: {}, content: {}, credential: {}", socialNetworkNotification.getSenderSocialNetworkId(), socialNetworkNotification.getReceiverSocialNetworkId(), title, content, credential);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("send finish from {}, to {}, title: {}, content: {}, credential: {}", socialNetworkNotification.getSenderSocialNetworkId(), socialNetworkNotification.getReceiverSocialNetworkId(), title, content, credential);

        socialNetworkNotification.setIsSuccess(Status.SUCCESS);
        return CompletableFuture.completedFuture(socialNetworkNotification);
    }
}
