package com.example.notiservice;

import com.example.notiservice.domain.Notification;
import com.example.notiservice.domain.channel.EmailNotification;
import com.example.notiservice.domain.channel.SMSNotification;
import com.example.notiservice.domain.channel.SocialNetworkNotification;
import com.example.notiservice.domain.system_profile.EmailSystemProfile;
import com.example.notiservice.domain.system_profile.SMSSystemProfile;
import com.example.notiservice.domain.system_profile.SocialNetworkSystemProfile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ObjectMapperTest {

    @Test
    void mapperTest() throws JsonProcessingException {
        EmailNotification emailNotification1 = new EmailNotification("transAction1", "email", null,"sender@naver.com", "reciver1@naver.com", new EmailSystemProfile("emailCredential"));
        EmailNotification emailNotification2 = new EmailNotification("transAction1", "email", null,"sender@naver.com", "reciver2@naver.com", new EmailSystemProfile("emailCredential"));

        SMSNotification smsNotification = new SMSNotification("transAction1", "sms", null, "010-1111-1111", "010-2222-2222", new SMSSystemProfile("smsCredential"));
        SocialNetworkNotification socialNetworkNotification = new SocialNetworkNotification("transAction1", "sns", null, "smsSenderId1", "smsReceiverId1", new SocialNetworkSystemProfile("snsCredential"));

        Notification notification = new Notification("senderId1", "testTitle", "testContent", "transAction1", List.of(emailNotification1, emailNotification2, smsNotification, socialNetworkNotification));

        ObjectMapper objectMapper = new ObjectMapper();

        String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(notification);

        System.out.println("s = " + jsonString);

        Notification notification1 = objectMapper.readValue(jsonString, Notification.class);

        System.out.println("notification1 = " + notification1);

    }
}
