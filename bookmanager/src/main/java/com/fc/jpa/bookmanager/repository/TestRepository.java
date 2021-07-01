package com.fc.jpa.bookmanager.repository;

import com.fc.jpa.bookmanager.domain.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestEntity, Long> {
}
