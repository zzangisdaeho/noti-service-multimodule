package com.example.notiservice.db.sql.repository;

import com.example.notiservice.db.sql.entity.CredentialEntity;
import com.example.notiservice.db.sql.entity.SystemType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialEntityRepository extends JpaRepository<CredentialEntity, SystemType> {
}
