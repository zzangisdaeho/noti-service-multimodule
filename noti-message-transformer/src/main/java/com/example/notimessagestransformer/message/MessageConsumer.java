package com.example.notimessagestransformer.message;

import com.example.noticore.message.KafkaHeaders;
import com.example.noticore.message.TopicConstList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

//@Component
@Slf4j
public class MessageConsumer {

    @KafkaListener(topics = TopicConstList.NOTIFICATION_SEND_CMD)
    public void test(@Header(value = KafkaHeaders.RETRY_COUNT, required = false) String headers, @Payload String data){
        log.info(headers + ":" + data);
    }

}
