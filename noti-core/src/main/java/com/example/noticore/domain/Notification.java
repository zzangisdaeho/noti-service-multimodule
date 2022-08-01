package com.example.noticore.domain;

import com.example.noticore.domain.channel.NotificationChannel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
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

    @Valid
    private List<NotificationChannel> notificationChannels;
}
