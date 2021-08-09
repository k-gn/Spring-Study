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

    // 영속성 컨텍스트에서 같은 트랜잭션 내에서의 작업은 cache 가 존재하기 때문에 이미 가지고 있는 데이터는
    // 다시 select 를 안쓰고 cache 에서 데이터를 가져온다.
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

        TestObj testObj1 = testRepository2.save(test1);
        TestObj testObj2 = testRepository2.save(test2);

        testService.find(1L);
    }
}