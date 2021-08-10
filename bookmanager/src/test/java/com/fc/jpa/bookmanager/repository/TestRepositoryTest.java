package com.fc.jpa.bookmanager.repository;

import com.fc.jpa.bookmanager.domain.TestEntity;
import com.fc.jpa.bookmanager.domain.TestObj;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TestRepositoryTest {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private TestRepository2 testRepository2;


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

    @Test
    @Transactional
    public void test2() {
//        TestEntity entity = new TestEntity();
//        entity.setName("test");
//        testRepository.save(entity);
//
//        testObj(entity);

        System.out.println(testRepository.findAll());
//        System.out.println(testRepository.findById(1L).get().getTestList());
        System.out.println("============================================");
//        System.out.println(testRepository2.findAll());
    }

    public List<TestObj> testObj(TestEntity entity) {

        TestObj testObj = new TestObj();
        testObj.setTestEntity(entity);
        entity.getTestList().add(testObj);
        testRepository2.save(testObj);

        TestObj testObj2 = new TestObj();
        testObj2.setTestEntity(entity);
        entity.getTestList().add(testObj2);
        testRepository2.save(testObj2);

        TestObj testObj3 = new TestObj();
        testObj3.setTestEntity(entity);
        entity.getTestList().add(testObj3);
        testRepository2.save(testObj3);

        return Arrays.asList(testObj, testObj2, testObj3);
    }

}