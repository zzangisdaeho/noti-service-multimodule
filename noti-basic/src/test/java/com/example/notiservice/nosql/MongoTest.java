package com.example.notiservice.nosql;

import com.example.notiservice.db.nosql.document.NotificationLog;
import com.example.notiservice.db.nosql.repository.NotificationLogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;


@SpringBootTest
public class MongoTest {

    @Autowired
    NotificationLogRepository notificationLogRepository;

    @Test
    void read(){
        List<NotificationLog> all = notificationLogRepository.findAll();

        System.out.println("all = " + all);
    }

    @Test
    void readOne(){
        Optional<NotificationLog> transAction1 = notificationLogRepository.findByTransactionId("transAction1");

        System.out.println("transAction1 = " + transAction1);
    }

//    @Test
//    void write(){
//        SuccessLog book = new SuccessLog();
//        book.setName("test1");
//        book.setAuthor("daeho1");
//        bookRepository.save(book);
//    }
}
