package com.fc.jpa.bookmanager.repository;

import com.fc.jpa.bookmanager.domain.TestEntity;
import com.fc.jpa.bookmanager.domain.TestObj;
import org.aspectj.weaver.ast.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository2 extends JpaRepository<TestObj, Long> {
}
