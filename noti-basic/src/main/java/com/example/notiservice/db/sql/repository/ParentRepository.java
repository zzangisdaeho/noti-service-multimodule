package com.example.notiservice.db.sql.repository;

import com.example.notiservice.db.sql.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, Long> {
}
