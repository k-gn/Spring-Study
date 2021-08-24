package com.example.querytest.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

// @Profile 어노테이션을 통해 스프링 환경설정을 할 수 있다.
// 각 환경에 맞게 Spring의 Bean들을 올릴 수 있어 아주 자주 사용되는 어노테이션
// 런타임 환경을 설정할 수 있는 기능을 제공
// @Profile 어노테이션에서 ! 와 같은 NOT 표현식이나 &, | 과 같은 논리연산자도 쓸 수 있다.
@Profile("local") // local 프로필이 활성화 될 때 이 빈을 등록
@Component
@RequiredArgsConstructor
public class InitMember {

    private final InitMemberService initMemberService;

    // 객체가 생성된 후 별도의 초기화 작업을 위해 실행하는 메소드를 선언한다.
    // @PostConstruct 어노테이션을 설정해놓은 init 메소드는 WAS가 띄워질 때 실행된다.
    // 의존성 주입이 이루어진 후 초기화를 수행하는 메서드
    @PostConstruct
    public void init() {
        initMemberService.init();
    }
    // @PostConstruct 는 해당 빈 자체만 생성되었다고 가정하에 호출되지만
    // 해당 빈에 관련한 AOP 등을 포함해서 전체 스프링 어플리케이션 컨택스트의 초기화를 말하지는 않는다.
    // 트랜잭션을 처리하는 AOP 등은 스프링 어플리케이션 컨택스트가 초기화가 되어야만 가능하다
    // 즉 @PostConstruct 만을 사용하면 @Transactional 을 이용하는게 가능하지 않다.
    // 하지만 여기서는 @PostConstruct 안에서 @Transactional 을 이용하는 빈을 호출해서 사용하니까
    // 이 빈이 초기화 되었다는건 @Transactional 을 이용할 수 있다는 시점을 말하므로 우회해서 사용하는게 가능하다.
    @Component
    static class InitMemberService {
        @PersistenceContext
        private EntityManager em;

        @Transactional
        public void init() {
            Team teamA = new Team("TeamA");
            Team teamB = new Team("TeamB");
            em.persist(teamA);
            em.persist(teamB);

            for (int i = 0; i < 100; i++) {
                Team selectedTeam = i % 2 == 0 ? teamA : teamB;
                em.persist(new Member("member" + i, i, selectedTeam));
            }
        }
    }
}