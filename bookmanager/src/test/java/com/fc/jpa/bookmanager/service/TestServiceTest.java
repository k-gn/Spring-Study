package com.fc.jpa.bookmanager.service;

import com.fc.jpa.bookmanager.domain.TestEntity;
import com.fc.jpa.bookmanager.domain.TestObj;
import com.fc.jpa.bookmanager.repository.TestRepository2;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestServiceTest {

    @Autowired
    private TestService testService;

    @Autowired
    private TestRepository2 testRepository2;

    @Test
    public void test() {
        TestEntity testEntity = new TestEntity();
        testEntity.setName("test");
        TestEntity test = testService.save(testEntity);

        TestObj test1 = new TestObj();
        test1.setTestEntity(test);
        test.getTestList().add(test1);

        TestObj test2 = new TestObj();
        test2.setTestEntity(test);
        test.getTestList().add(test2);

        System.out.println("TestEntity : " + test);
//        System.out.println("TestEntity.getTestList : " + test.getTestList());

        TestObj testObj1 = testRepository2.save(test1);
        System.out.println("testObj1 : " + testObj1);
        TestObj testObj2 = testRepository2.save(test2);
        System.out.println("testObj2 : " + testObj2);

        testService.find(1L);
    }
}