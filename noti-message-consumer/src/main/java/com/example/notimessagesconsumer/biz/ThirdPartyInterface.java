package com.example.notimessagesconsumer.biz;

import com.example.noticore.domain.request_all.channel.NotificationChannel;

import java.util.concurrent.CompletableFuture;

public interface ThirdPartyInterface {

    boolean support(Object channel);

    CompletableFuture<NotificationChannel> send(Object channel, String title, String content);
}
