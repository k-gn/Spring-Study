package com.fc.jpa.bookmanager.repository;

import com.fc.jpa.bookmanager.domain.TestEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TestRepositoryTest {

    @Autowired
    private TestRepository testRepository;

    @Test
    public void test() {

        TestEntity entity = new TestEntity();
        entity.setName("test");

        testRepository.save(entity);
        System.out.println(testRepository.findAll());
        entity.setName("test update");
        testRepository.save(entity);
        System.out.println(testRepository.findAll());
    }
}