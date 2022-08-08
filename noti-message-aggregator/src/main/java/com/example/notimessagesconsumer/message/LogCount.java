package com.example.notimessagesconsumer.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogCount {
    private int count;

    public LogCount add(String value) {
        ++ this.count;
        return this;
    }
}
