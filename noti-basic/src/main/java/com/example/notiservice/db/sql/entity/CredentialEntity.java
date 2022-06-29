package com.example.notiservice.db.sql.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CredentialEntity {

    @Id
    @Enumerated(EnumType.STRING)
    private SystemType systemType;

    private String credential;
}
