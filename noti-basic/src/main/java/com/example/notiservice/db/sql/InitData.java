package com.example.notiservice.db.sql;

import com.example.notiservice.db.sql.entity.CredentialEntity;
import com.example.notiservice.db.sql.entity.SystemType;
import com.example.notiservice.db.sql.repository.CredentialEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitData {

    private final CredentialEntityRepository credentialEntityRepository;

    @PostConstruct
    @Transactional
    public void init(){
        CredentialEntity emailCredential = new CredentialEntity(SystemType.EMAIL, "emailCredential");
        CredentialEntity smsCredential = new CredentialEntity(SystemType.SMS, "smsCredential");
        CredentialEntity snsCredential = new CredentialEntity(SystemType.SNS, "snsCredential");
        credentialEntityRepository.saveAll(List.of(emailCredential, smsCredential, snsCredential));
    }
}
