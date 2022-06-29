package com.example.notiservice.controller;

import com.example.notiservice.db.nosql.document.NotificationLog;
import com.example.notiservice.db.nosql.repository.NotificationLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NotificationLogController {

    private final NotificationLogRepository notificationLogRepository;

    @GetMapping(value = "/notification/logs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<NotificationLog> showLogs(@RequestParam(required = true) String senderId, Pageable pageable){

        Page<NotificationLog> allBySenderId = notificationLogRepository.findAllBySenderId(senderId, pageable);
        return allBySenderId;
    }

}
