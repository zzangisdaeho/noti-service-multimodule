package com.example.notiservice.controller;

import com.example.notiservice.domain.Notification;
import com.example.notiservice.domain.channel.EmailNotification;
import com.example.notiservice.domain.channel.SMSNotification;
import com.example.notiservice.domain.channel.SocialNetworkNotification;
import com.example.notiservice.domain.system_profile.EmailSystemProfile;
import com.example.notiservice.domain.system_profile.SMSSystemProfile;
import com.example.notiservice.domain.system_profile.SocialNetworkSystemProfile;
import com.example.notiservice.messaging.KafkaProducerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final KafkaProducerService kafkaProducerService;
    private final ObjectMapper objectMapper;

    @PostMapping("/kafka")
    public void sendMessage(@RequestBody Object request){
//        EmailNotification emailNotification1 = new EmailNotification("transAction1", "sender@naver.com", "reciver1@naver.com", new EmailSystemProfile("emailCredential"));
//        EmailNotification emailNotification2 = new EmailNotification("transAction1", "sender@naver.com", "reciver2@naver.com", new EmailSystemProfile("emailCredential"));
//
//        SMSNotification smsNotification = new SMSNotification("transAction1", "010-1111-1111", "010-2222-2222", new SMSSystemProfile("smsCredential"));
//        SocialNetworkNotification socialNetworkNotification = new SocialNetworkNotification("transAction1", "smsSenderId1", "smsReceiverId1", new SocialNetworkSystemProfile("snsCredential"));
//
//        Notification notification = new Notification("senderId1", "testTitle", "testContent", "transAction1", List.of(emailNotification1, emailNotification2, smsNotification, socialNetworkNotification));


        String jsonInString = null;

        try{
            jsonInString = objectMapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        kafkaProducerService.sendStartMessage(jsonInString);
    }
}
