package com.example.notiservice.db.sql.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Child {

    @Id
    @GeneratedValue
    private Long childSeq;

    private String childName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentSeq")
    private Parent parent;
}
