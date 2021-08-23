package com.example.querytest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;

@SpringBootApplication
public class QueryTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(QueryTestApplication.class, args);
    }

    // JPQL 대신 Querydsl 을 이용하는 이유 중 하나가 type-safe (컴파일 시점에 알 수 있는) 쿼리를 날리기 위해서 사용한다.
    // 이 말은 JPQL 에서 쿼리에서 오타가 발생해도 컴파일 시점에서 알기 힘들다. 오로지 런타임에서만 체크가 가능하다.
    // 하지만 Querydsl 은 컴파일 시점에 오류를 잡아줄 수 있기 떄문에 좋다.
    // 동적 쿼리 작성이 편리하다.
    // Querydsl 동작 하는 과정은 JPQL 을 거쳐서 SQL 로 변환되서 실행한다.
}
