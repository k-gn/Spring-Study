package com.example.querytest.repository;


import com.example.querytest.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

// Querydsl 실무 활용 - 스프링 데이터 JPA와 Querydsl
// 스프링 데이터 JPA 를 이용하면 순수 JPA 를 이용해서 만들었던 기본적인 기능들은 제공해주므로 간단해진다.
// 기본적인 save() 함수나 findById 같은 메소드들은 모두 스프링 데이터 JPA 에서 제공해준다.

// 복잡한 쿼리를 쓰려면 사용자 정의 레파지토리를 작성해야 한다.
// 복잡한 쿼리를 담당한 MemberRepositoryCustom 인터페이스를 만들고 여기에 선언을 해준다.
// 그 후 실제 구현을 담당한 MemberRepositoryImpl 이라는 클래스를 만들고 MemberRepositoryCustom 을 상속한다.
// 이때 이름이 스프링 데이터 JPA 레파지토리 이름을 따라가야 실제 구현체를 찾을 수 있으므로 이름에 조심하자.

// 스프링 데이터 JPA가 제공하는 Querydsl 기능
//  - 인터페이스 지원 - QuerydslPredicateExecutor
//  - Querydsl Web 지원 : 컨트롤러 레벨에서 Predicate 를 받을 수 있도록 QuerydslPredicateArgumentResolver 룰 지원
public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom, QuerydslPredicateExecutor<Member> {

    // select m from Member m where m.username = ?
    List<Member> findByUsername(String username);
}
