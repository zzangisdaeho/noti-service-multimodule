package com.example.notiservice.service;

import com.example.notiservice.db.sql.entity.SystemType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CredentialServiceTest {

    @Autowired
    private CredentialService credentialService;

    @Test
    void cacheTTL(){
        String credential = credentialService.getCredential(SystemType.EMAIL);
        System.out.println("credential = " + credential);
    }

}