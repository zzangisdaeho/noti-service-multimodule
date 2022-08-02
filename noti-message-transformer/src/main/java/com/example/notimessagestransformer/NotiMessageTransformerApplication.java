package com.example.notimessagestransformer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
public class NotiMessageTransformerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotiMessageTransformerApplication.class, args);
    }
}
