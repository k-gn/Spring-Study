package com.example.querytest;

import com.example.querytest.domain.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class QueryTestClazzApplicationTests {

    @Autowired
    EntityManager em;

    @Test
    void contextLoads() {
        Hello hello = new Hello();
        em.persist(hello);

        JPAQueryFactory query = new JPAQueryFactory(em);
        QHello qHello = QHello.hello; //Querydsl Q타입 동작 확인

        Hello result = query
                .selectFrom(qHello)
                .fetchOne();

        Assertions.assertThat(result).isEqualTo(hello);
        //lombok 동작 확인 (hello.getId())
        Assertions.assertThat(result.getId()).isEqualTo(hello.getId());
    }

    @Test
    void test() {
        TestClazz test = new TestClazz();
        em.persist(test);

        JPAQueryFactory query = new JPAQueryFactory(em);

        QTestClazz qTestClazz = QTestClazz.testClazz;

        TestClazz testClazz = query.selectFrom(qTestClazz).fetchOne();
    }

    @Test
    void test2() {
        TestClazz2 test = new TestClazz2();
        em.persist(test);

        JPAQueryFactory query = new JPAQueryFactory(em);

        QTestClazz2 qTestClazz2 = QTestClazz2.testClazz2;

        TestClazz2 testClazz2 = query.selectFrom(qTestClazz2).fetchOne();
    }

}
