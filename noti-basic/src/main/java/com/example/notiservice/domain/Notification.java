package com.example.notiservice.domain;

import com.example.notiservice.domain.channel.NotificationChannel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @NotEmpty
    private String senderId;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    @NotEmpty
    private String transactionId;

    @Valid
    private List<NotificationChannel> notificationChannels;
}
