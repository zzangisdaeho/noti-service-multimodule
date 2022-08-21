package com.example.notimessagesconsumer.biz;

import com.example.noticore.domain.request_all.channel.NotificationChannel;
import com.example.noticore.domain.request_all.channel.SocialNetworkNotification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class SNSBiz implements ThirdPartyInterface{

    @Override
    public boolean support(Object channel) {
        return channel instanceof SocialNetworkNotification;
    }

    @Override
    @Async
    public CompletableFuture<NotificationChannel> send(Object channel, String title, String content) {
        SocialNetworkNotification socialNetworkNotification = (SocialNetworkNotification) channel;
        log.info("sending SNS... from {}, to {}, title: {}, content: {}", socialNetworkNotification.getSenderSocialNetworkId(), socialNetworkNotification.getReceiverSocialNetworkId(), title, content);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        throw new IllegalStateException("망가졌당");
        log.info("send finish from {}, to {}, title: {}, content: {}", socialNetworkNotification.getSenderSocialNetworkId(), socialNetworkNotification.getReceiverSocialNetworkId(), title, content);

        return CompletableFuture.completedFuture(socialNetworkNotification);
    }
}
