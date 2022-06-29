package com.example.notiservice.db.nosql.repository;

import com.example.notiservice.db.nosql.document.NotificationLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationLogRepository extends MongoRepository<NotificationLog, String> {

    Optional<NotificationLog> findByTransactionId(String transactionId);

    Page<NotificationLog> findAllBySenderId(String senderId, Pageable pageable);
}
