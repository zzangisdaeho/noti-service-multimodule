package com.example.notiservice.event;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class SuccessEventDto {

    private Object successResult;

    private LocalDateTime occurTime;

    public SuccessEventDto(Object successResult) {
        this.successResult = successResult;
        this.occurTime = LocalDateTime.now();
    }
}
