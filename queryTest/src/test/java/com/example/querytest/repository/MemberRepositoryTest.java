package com.example.querytest.repository;

import com.example.querytest.domain.Member;
import com.example.querytest.domain.QMember;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    // QuerydslPredicateExecutor 에서 정의한 메소드들은 Querydsl Predicate 조건절을 넣을 수 있다. 이를 스프링 데이터 JPA에서 지원해준다.
    // 다만 한계점은 조인을 할 수 없다는 단점이 있다.
    // 그리고 또 다른 단점은 MemberRepository 같은 레파지토리에 Predicate 파라미터를 넘겨줘야 한다.
    // 근데 이는 서비스나 컨트롤러 계층에서 직접 만들어서 넘겨줘야 하는데 이는 강한 결합이 일어나서 바꾸는데 좋지 않다.
    @Test
    void querydslPredicateExecutorTest(){
        //given
        //when
        QMember member = QMember.member;
        Iterable<Member> result = memberRepository.findAll(
                member.age.between(0, 40)
                        .and(member.username.eq("member1"))
        );
        //then
        for (Member findMember : result) {
            System.out.println(findMember);
        }
    }
}