package com.example.template.repository;

import com.example.template.model.EntityClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EntityRepository extends JpaRepository<EntityClass, UUID> {
}
