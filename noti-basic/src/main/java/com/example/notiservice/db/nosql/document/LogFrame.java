package com.example.notiservice.db.nosql.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public abstract class LogFrame {

    private LocalDateTime committedAt;

    private Object details;

    private String title;

    private String content;

    private String transactionId;

    private String senderId;
}
