package com.example.notiservice.service;

import com.example.notiservice.config.RedisConfig;
import com.example.notiservice.db.sql.entity.CredentialEntity;
import com.example.notiservice.db.sql.entity.SystemType;
import com.example.notiservice.db.sql.repository.CredentialEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CredentialService {

    private final CredentialEntityRepository credentialEntityRepository;

    @Cacheable(value = RedisConfig.RedisCacheKey.CREDENTIAL, key = "#type")
    public String getCredential(SystemType type){
        log.info("================cacheable logic =================");
        return credentialEntityRepository.findById(type).orElseThrow(() -> new IllegalStateException("값이.. 없네??")).getCredential();
    }

    @CachePut(value = RedisConfig.RedisCacheKey.CREDENTIAL, key = "#credentialEntity.systemType")
    public String updateCredential(CredentialEntity credentialEntity){
        log.info("================cacheput logic =================");
        return credentialEntity.getCredential();
    }

}
