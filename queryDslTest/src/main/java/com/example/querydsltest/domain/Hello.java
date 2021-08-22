package com.example.querydsltest.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Hello {

    @Id
    @GeneratedValue
    private Long id;
}

// QueryDSL 사용 이유
// SQL을 java로 type-safe하게 개발 할 수 있게 해주는 프레임워크
// Querydsl 정적 타입을 이용해서 SQL과 같은 쿼리를 생성할 수 있도록 해 주는 프레임워크
// SQL, JPQL을 코드로 작성 (컴파일 시점에 문법 오류를 발견)
// 동적 쿼리 작성이 편리