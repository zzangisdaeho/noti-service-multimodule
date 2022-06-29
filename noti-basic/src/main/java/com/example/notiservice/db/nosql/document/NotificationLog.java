package com.example.notiservice.db.nosql.document;

import com.example.notiservice.domain.Notification;
import com.example.notiservice.domain.channel.NotificationChannel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Document(collection = "notificationLogs")
@Getter
@NoArgsConstructor
@ToString
public class NotificationLog extends LogFrame{

    @Id
    private String _id;

    public NotificationLog(NotificationChannel notificationChannel, String title, String content, String senderId) {
        super(LocalDateTime.now(), notificationChannel, title, content, notificationChannel.getTransactionId(), senderId);
    }

    public NotificationLog(Notification notification){
        super(LocalDateTime.now(), notification, notification.getTitle(), notification.getContent(), notification.getTransactionId(), notification.getSenderId());
    }
}
