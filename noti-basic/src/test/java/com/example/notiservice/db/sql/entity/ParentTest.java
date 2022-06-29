package com.example.notiservice.db.sql.entity;

import com.example.notiservice.db.sql.repository.ParentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ParentTest {

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void initData(){
        Parent parent = new Parent();
        parent.setName("parent1");

        Child child1 = new Child();
        child1.setChildName("child1");

        Child child2 = new Child();
        child2.setChildName("child2");

        parent.addChild(child1);
        parent.addChild(child2);

        parentRepository.save(parent);
        entityManager.flush();
        entityManager.clear();
    }

    @Test
    @Transactional
    @Commit
    void testOrdering(){
        Parent parent = parentRepository.findById(1L).get();

        Child child1 = parent.getChildren().get(0);
        Child child2 = parent.getChildren().get(1);

        parent.getChildren().clear();

        parent.addChild(child2);
        parent.addChild(child1);

    }
}