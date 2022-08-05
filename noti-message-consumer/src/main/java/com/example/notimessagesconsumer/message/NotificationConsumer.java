package com.example.notimessagesconsumer.message;

import com.example.noticore.domain.request_all.channel.NotificationChannel;
import com.example.noticore.message.KafkaHeaders;
import com.example.noticore.message.TopicConstList;
import com.example.notimessagesconsumer.biz.ThirdPartyInterface;
import com.example.notimessagesconsumer.service.NotificationSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationConsumer {


    private final NotificationSender notificationSender;
    private final NotificationFailProducer notificationFailProducer;

    @KafkaListener(topics = TopicConstList.NOTIFICATION_SEND_SPLIT)
    public void notificationConsumer(ConsumerRecord<String,String> record){
        byte[] value = record.headers().lastHeader(KafkaHeaders.RETRY_COUNT).value();
        int retryCount = Integer.parseInt(new String(value));

        if(retryCount > 2){
            notificationFailProducer.sendFailMessage(record);
        }else{
            notificationSender.send(record);
        }

    }



}
