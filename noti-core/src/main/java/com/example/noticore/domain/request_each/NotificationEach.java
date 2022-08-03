package com.example.noticore.domain.request_each;

import com.example.noticore.domain.request_all.channel.NotificationChannel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEach {

    private String transactionId;
    private NotificationChannel notificationChannel;
    private String title;
    private String content;
    private int index;
    private int total;
}
