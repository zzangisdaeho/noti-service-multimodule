package com.example.notiservice.biz;

import com.example.notiservice.domain.channel.NotificationChannel;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;

public interface ThirdPartyInterface {

    boolean support(Object channel);

    CompletableFuture<NotificationChannel> send(Object channel, String title, String content);
}
