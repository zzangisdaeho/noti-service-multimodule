package com.example.notiservice.db.sql.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Parent {

    @Id
    @GeneratedValue
    private Long parentSeq;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn
    @OrderColumn(name = "childOrder")
    private List<Child> children = new ArrayList<>();

    public void addChild(Child child){
        this.children.add(child);
        child.setParent(this);
    }
}
