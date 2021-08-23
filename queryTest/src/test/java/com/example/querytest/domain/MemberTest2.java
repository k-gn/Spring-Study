package com.example.querytest.domain;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

import static com.example.querytest.domain.QMember.member;
import static com.example.querytest.domain.QTeam.team;
import static com.querydsl.core.types.dsl.Expressions.constant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
//@Commit
public class MemberTest2 {

    @Autowired
    EntityManager em;

    JPAQueryFactory queryFactory;

    @PersistenceUnit // @PersistenceUnit 을 통해 EntityManagerFactory를 얻을 수 있다.
    EntityManagerFactory emf;

    @BeforeEach
    public void before() {
        queryFactory = new JPAQueryFactory(em);

        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
    }

    // Projection 과 결과 반환
    // Projection 은 엔터티를 그냥 그대로 가지고 오는게 아니라 필요한 필드만 가지고 오는 걸 말한다.
    // Querydsl 에서는 프로젝션 대상이 하나면 명확한 타입을 지정할 수 있지만 프로젝션 대상이 둘 이상이라면 Tuple 이나 DTO 로 조회해야 한다.
    @Test
    void projectionOne(){
        //given

        // 프로젝션 대상이 하나일 경우 예제
        //when
        List<String> result = queryFactory
                .select(member.username)
                .from(member)
                .fetch();
        //then
        for(String s : result){
            System.out.println(s);
        }
    }

    @Test
    void projectionTwo(){
        //given

        // 프로젝션 대상이 두개 이상인 경우에 예제
        //when
        List<Tuple> result = queryFactory
                .select(member.username, member.age)
                .from(member)
                .fetch();
        //then
        for(Tuple tuple : result) {
            System.out.println(tuple.get(member.username));
            System.out.println(tuple.get(member.age));
        }
    }

    // 기존에 Jpa 를 이용해서 Dto 를 만드는 예제
    @Test
    void projectionWithJpa(){
        //given

        //when
        List<MemberDto> result = em.createQuery(
                // 순수 JPA 에서 DTO 를 조회할 때는 new 키워드를 이용한 생성자를 통해서만 가능했고
                // 그리고 package 이름을 모두 명시해야해서 좀 지저분함이 있었다.
                "select new com.example.querytest.domain.MemberDto(m.username, m.age)" +
                        "from Member m", MemberDto.class
        )
                .getResultList();
        //then
        for (MemberDto memberDto : result){
            System.out.println(memberDto.toString());
        }
    }

    // Querydsl 을 이용한 빈 생성
    // DTO 를 반환하는 방법이 크게 3가지가 있다.
    // 1. 프로퍼티로 접근하는 방식 (Setter 사용)
    // 2. 필드 직접 접근
    // 3. 생성자를 사용

    @Test
    void findDtoBySetter(){
        //given
        //when
        List<MemberDto> result = queryFactory
                // Projections.bean() 을 사용하면 기본 생성자와 setter 를 통해서 객체를 만들게 된다.
                .select(Projections.bean(MemberDto.class,
                        member.username,
                        member.age))
                .from(member)
                .fetch();
        //then
        for (MemberDto memberDto : result) {
            System.out.println(memberDto.toString());
        }
    }

    @Test
    void findDtoByField(){
        //given
        //when
        List<MemberDto> result = queryFactory
                // Projections.fields() 를 통해서 getter setter 필요없이 바로 필드로 직접 주입해서 사용한다.
                // private 로 선언해도 상관없다. 사실상 자바 리플렉션을 이용하면 private 상관없이 다 알수있다.
                // 필드 주입할땐 dto 필드 이름과 QMember.member 의 필드 이름과 매칭이 되야 한다. 그래야 찾을 수 있다.
                .select(Projections.fields(MemberDto.class,
                        member.username,
                        member.age))
                .from(member)
                .fetch();
        //then
        for (MemberDto memberDto : result) {
            System.out.println(memberDto.toString());
        }
    }

    @Test
    void findUserDto(){
        //given

        //when
        List<UserDto> result = queryFactory
                .select(Projections.fields(UserDto.class,
                        member.username.as("name"), // 필드 이름이 서로 다를 경우
                        member.age))
                .from(member)
                .fetch();
        //then
        for (UserDto userDto : result){
            assertNotNull(userDto.getName());
        }
    }

    @Test
    void findUserDtoBySubQuery(){
        //given
        QMember memberSub = new QMember("memberSub");
        //when
        List<UserDto> result = queryFactory
                //  subQuery 를 이용하는 경우
                .select(Projections.fields(UserDto.class,
                        member.username.as("name"),
                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(memberSub.age.max())
                                        .from(memberSub), "age" // 별칭으로 맞춰주는게 중요
                        )))
                .from(member)
                .fetch();
        //then
        for (UserDto userDto : result){
            assertNotNull(userDto.getName());
            assertEquals(40, userDto.getAge()); // 최대 나이가 40살 이다.
        }
    }

    @Test
    void findDtoByConstructor(){
        //given
        //when
        List<MemberDto> result = queryFactory
                // 생성자를 이용해 생성하는 방법
                // Projections.constructor() 를 이용해서 생성자 를 통해서 Dto 를 만들 수 있다.
                .select(Projections.constructor(MemberDto.class,
                        member.username,
                        member.age))
                .from(member)
                .fetch();
        //then
        for (MemberDto memberDto : result) {
            System.out.println(memberDto.toString());
        }
    }

    // Projection 과 결과 반환 - @QueryProjection
    // 프로젝션을 이용한 방법 중에 가장 깔끔한 방법일 수 있다.
    // @QueryProjection 을 이용해 DTO 도 Q타입의 클래스를 만들어서 이를 이용해 바로 만드는 방법이다.
    // Q타입의 클래스를 제공해주니 type-safe 하다는 장점이 있다.
    @Test
    void findDtoByQueryProjection(){
        //given

        // Projections.constructor() 와의 차이는 컴파일 오류를 못잡는다는 문제가 생긴다. 위 방식이 좀 더 안정성이 있다.
        // 다만 이 방식의 문제점은 Querydsl 에 대한 의존성을 가지게 된다는 점이다. 라이브러리를 바꾸게 된다면 고쳐야할 DTO 가 많아진다는 단점이 있다.
        //when
        List<MemberDto> result = queryFactory
                .select(new QMemberDto(member.username, member.age))
                .from(member)
                .fetch();
        //then
        for (MemberDto memberDto : result) {
            System.out.println(memberDto.toString());
        }
    }

    // 동적 쿼리 - BooleanBuilder 사용
    // 실행시에 쿼리 문장이 만들어져 실행되는 쿼리문을 동적 쿼리라고 하는데 동적으로 변수를 받아서 쿼리가 완성되는 걸 말한다.
    // Querydsl 에서 동적 쿼리를 만드는 방법은 두가지 방식이 있다.
    // 1. BooleanBuilder
    // 2. Where 다중 피라미터 사용

    // BooleanBuilder 사용예제
    @Test
    void dynamicQueryUsingBooleanBuilder(){
        //given
        String usernameParam = "member1";
        Integer ageParam = 10;
        //when
        List<Member> result = searchMember1(usernameParam, ageParam);
        //then
        assertEquals(1, result.size());
    }

    private List<Member> searchMember1(String usernameParam, Integer ageParam) {
        // BooleanBuilder 객체를 생성할때 초기값을 넣어줄 수도 있다.
        // BooleanBuilder 간 and 로 연결 가능
        // BooleanExpression 를 생성 후 BooleanBuilder and 로 연결 가능
//        BooleanBuilder builder = new BooleanBuilder(member.username.eq(usernameParam));
        BooleanBuilder builder = new BooleanBuilder();
        if(usernameParam != null) {
            builder.and(member.username.eq(usernameParam));
        }
        if(ageParam != null) {
            builder.and(member.age.eq(ageParam));
        }
        return queryFactory
                .selectFrom(member)
                .where(builder) // where 절에 BooleanBuilder 넣어주기
                .fetch();
    }

    // 동적 쿼리 - Where 다중 파라미터 사용
    @Test
    void dynamicQueryUsingWhereParameter(){
        //given
        String usernameParam = "member1";
        Integer ageParam = 10;
        //when
        List<Member> result = searchMember2(usernameParam, ageParam);
        //then
        assertEquals(1, result.size());
    }

    private List<Member> searchMember2(String usernameCond, Integer ageCond) {
        return queryFactory
                .selectFrom(member)
                .where(usernameEq(usernameCond), ageEq(ageCond)) // , 나열 방식에서 null 값은 무시된다.
                .fetch();
    }

    // usernameEq() 메소드가 null 을 리턴하게 되면 Where() 에 null 값이 들어가게 되는데 이는 무시가 된다. 그러므로 동적 쿼리가 될 수 있다.
    // BooleanBuilder 를 보는 것보다 Where 절에 적절한 메소드를 넣음으로써 가독성을 높일 수 있다. BooleanBuilder 는 객체를 또 봐야한다.
    private Predicate usernameEq(String usernameCond) {
        if(usernameCond == null) return null;

        return member.username.eq(usernameCond);
    }

    private Predicate ageEq(Integer ageCond) {
        if(ageCond == null) return null;

        return member.age.eq(ageCond);
    }

    // Where 피라미터 조립 예제
    // 조건 조립을 통해서 추상화를 적절히 할 수 있다는 장점과 재사용성이 높다는 장점이 있다.
    @Test
    void dynamicQueryUsingWhereParameter2(){
        //given
        String usernameParam = "member1";
        Integer ageParam = 10;
        //when
        List<Member> result = searchMember3(usernameParam, ageParam);
        //then
        assertEquals(1, result.size());
    }

    // booleanbuilder 로 여러 if 절을 사용해 동적 쿼리 작성
    // 또는 BooleanExpression 을 사용해 아래처럼 동적 쿼리 작성
    // 결과적으로 where를 처리하는 기능(목적)은 같은데, 생성하는 방법과 조건을 추가하는 방식에 차이가 있다.
    private List<Member> searchMember3(String usernameParam, Integer ageParam) {
        return queryFactory
                .selectFrom(member)
                .where(allEq(usernameParam, ageParam)) // BooleanExpression 넣어주기, null 값은 무시된다.
                .fetch();
    }

    private BooleanExpression allEq(String usernameParam, Integer ageParam) {
        return usernameEq1(usernameParam).and(ageEq(ageParam));
    }

    private BooleanExpression usernameEq1(String usernameCond) {
        if(usernameCond == null) return null;
        return member.username.eq(usernameCond);
    }

    private BooleanExpression ageEq1(Integer ageCond) {
        if(ageCond == null) return null;
        return member.age.eq(ageCond);
    }

    // 수정 및 삭제 배치쿼리
    // 쿼리 한번으로 대량의 데이터를 수정하는 방식에 관한 것이다. 
    // 여러 건(대량의 데이터)을 한 번에 수정하거나 삭제하는 방법을 벌크 연산이라고 한다. (execute 사용)
    // 벌크 연산은 조심해야 되는게 있다. JPA 에는 영속성 컨택스트가 메모리에 올라와 있다.
    // 하지만 벌크 연산은 DB 에 바로 반영하는거기 때문에 영속성 컨택스트의 상태와 DB 의 상태가 달라지게 된다.
    // 즉 벌크 연산을 한 후에 fetch() 로 데이터를 조회할려고 해도 영속성 컨택스트에 값이 있다면 변경된 값을 DB 에서 가지고 와도 1차 캐시에 있는 값을 전달해준다.
    // 변경된 값을 가지고 오기 위해서는 em.flush() 와 em.clear() 를 통해서 영속성 컨택스트 값을 버리면 된다.

    // 수정
    @Test
    void bulkUpdate(){
        //given

        //when
        long count = queryFactory
                .update(member)
                .set(member.username, "비회원")
                .where(member.age.lt(28))
                .execute();
        //then
        assertEquals(2, count);
    }

    @Test
    @DisplayName("벌크 수정 연산 후 데이터 가져오기 - 영속성 컨택스트에서 가져오므로 반영이 안됨.")
    void bulkUpdateAndFetch(){
        //given

        //when
        queryFactory
                .update(member)
                .set(member.username, "비회원")
                .where(member.age.lt(28))
                .execute();

        List<Member> result = queryFactory
                .selectFrom(member)
                .fetch();
        //then
        for (Member member : result) {
            System.out.println(member.getUsername() + " " + member.getAge());
        }
    }

    @Test
    void bulkAdd(){
        //given

        //when
        long count = queryFactory
                .update(member)
                .set(member.age, member.age.add(1))
                .execute();
        //then
        assertEquals(4, count);
    }

    @Test
    void bulkMultiply(){
        //given

        //when
        long count = queryFactory
                .update(member)
                .set(member.age, member.age.multiply(2))
                .execute();
        //then
        assertEquals(4, count);
    }

    // 삭제
    @Test
    void bulkDelete(){
        //given

        //when
        long count = queryFactory
                .delete(member)
                .where(member.age.gt(18))
                .execute();
        //then
    }

    // SQL Function 호출하기
    // SQL Function 은 JPA 와 같이 Dialect 에 등록된 내용만 호출할 수 있다.
    @Test
    void sqlFunction(){
        //given

        //when
        List<String> result = queryFactory
                .select(Expressions.stringTemplate("function('replace',{0},{1},{2})"
                        , member.username, "member", "M"))
                .from(member)
                .fetch();
        //then
        for (String s : result){
            System.out.println(s);
        }
    }
}