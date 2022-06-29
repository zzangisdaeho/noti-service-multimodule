package com.example.notiservice.event;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FailEventDto {

    private String reason;

    private LocalDateTime occurTime;

    private Exception e;

    public FailEventDto(String reason, Exception e) {
        this.reason = reason;
        this.occurTime = LocalDateTime.now();
        this.e = e;
    }
}
