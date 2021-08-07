package com.fc.jpa.bookmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//@EnableJpaAuditing
public class BookmanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookmanagerApplication.class, args);
    }

}

/*
    # ORM ?
    - 자바객체와 데이터베이스 테이블(엔티티)을 연결해주는 개념
    # JPA ?
    - 자바 진영의 ORM 표준 인터페이스
    # Hibernate ?
    - 실제 JPA 표준 구현체를 제공
    # Spring Data JPA ?
    - Hibernate 를 좀 더 사용하기 쉽도록 스프링에서 제공
*/
