package com.example.querytest.domain;

import com.example.querytest.dto.MemberTeamDto;
import com.example.querytest.dto.QMemberTeamDto;
import com.example.querytest.util.OrderByNull;
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
import java.util.ArrayList;
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
    // # 동적쿼리는 BooleanExpression 사용하는게 좋아보인다.
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

    // exist 메소드 사용하지 않기
    // Querydsl 에서 exist 는 사용하지 않는 것이 좋다. 왜냐하면 Querydsl 에 있는 exist 는 count 쿼리를 사용하기 떄문이다.
    // SQL exist 쿼리의 경우에는 첫번째로 조건에 맞는 값을 찾는다면 바로 반환하도록 하지만 count 쿼리는 전체 행을 모두 조회하도록 해서 성능이 떨어진다.
    // Querydsl 에서는 이 메소드를 사용하지 않고 우회하도록 해야하는데 이를 위해서는 fetchFirst() 를 사용하면 된다.
    public Boolean exist(Long memberId) {
        Integer fetchOne = queryFactory
                .selectOne()
                .from(member)
                .where(member.id.eq(memberId))
                .fetchFirst();

        return fetchOne != null;
    }

    // Cross Join을 회피하기
    // 묵시적 조인이라고 하는 조인을 명시하지 않고 엔터티에서 다른 엔터티를 조회해서 비교하는 경우 JPA 가 알아서 크로스 조인을 하게 된다.
    // 크로스 조인을 하게 되면 나올 수 있는 데이터가 그냥 조인들보다 많아지기 때문에 성능상에 단점이 있다.
    // 명시적 조인을 이용해서 해결하자.
    // 크로스 조인 발생 예제
    public List<Member> crossJoin() {
        return queryFactory
                .selectFrom(member)
                .where(member.team.id.gt(member.team.id))
                .fetch();
    }
    // Cross Join 을 InnerJoin 으로 변경
    public List<Member> crossJoinToInnerJoin() {
        return queryFactory
                .selectFrom(member)
                .innerJoin(member.team, team)
                .where(member.team.id.gt(member.team.id))
                .fetch();
    }

    // 조회할땐 Entity 보다는 Dto 를 우선적으로 가져오기
    // Entity 를 가지고 오면 영속성 컨택스트의 1차 캐시 기능을 사용하게 되기도 하고 불필요한 칼럼을 조회하기도 한다.
    // 실시간으로 Entity 변경이 필요한 경우엔 Entity 조회를 하자.
    // 성능 개선이나 대량의 데이터 조회가 필요한 경우엔 Dto 조회를 하자.
    // Dto 조회를 할 때 좀 더 성능상으로 이득을 보기 위해서는 조회 칼럼을 최소화 하는 방법이 있다.
    public List<MemberTeamDto> findSameTeamMember(Long teamId){
        return queryFactory
                // select 절에 보면 필요한 칼럼만 데이터베이스에서 가져오도록 하는데
                // teamId 같은 경우는 이미 기존에 매개변수로 받았으니까 데이터베이스에서 가져올 필요는 없다.
                // 이처럼 중복된 칼럼은 가져오지 않도록 해서 성능상에서 약간의 이득을 더 볼 수 있다.
                //  Dto 클래스 생성자에 @QueryProjection 을 해서 Dto 도 Q타입의 클래스를 만들도록 했다.
                //  이를 통해 기존의 Projections 할때보다 타입 세이프 하다는 장점이 있다. 물론 Querydsl 에 더 의존적이라는 단점도 있다.
                .select(new QMemberTeamDto(
                        member.id,
                        member.username,
                        member.age,
                        Expressions.asNumber(teamId), // 상수 사용하기 (이미 있는 값은 그대로 사용할 목적)
                        team.name
                ))
                .from(member)
                .innerJoin(member.team, team)
                .where(member.team.id.eq(teamId))
                .fetch();
    }

    // Select 칼럼에 Entity는 자제하기
    // select 절 안에 Entity 를 넣도록 하면 Entity 에 있는 모든 칼럼들이 조회가 된다
    // Select 절에 Entity가 있는 경우
//    public List<MemberTeamDto2> entityInSelect(Long teamId){
//        return queryFactory
//                .select(
//                        new QMemberTeamDto2(
//                                member.id,
//                                member.username,
//                                member.age,
//                                member.team // team 에 있는 모든 칼럼을 가지고오게 된다.
//                        )
//                )
//                .from(member)
//                .innerJoin(member.team, team)
//                .where(member.team.id.eq(teamId))
//                .fetch();
//    }
    // Select 절에 필요한 칼럼만 있는 경우
//    public List<MemberTeamDto2> entityInSelect(Long teamId){
//        return queryFactory
//                .select(
//                        new QMemberTeamDto2(
//                                member.id,
//                                member.username,
//                                member.age,
                                // select 절에 Entity 를 사용할 땐 Entity 자체가 필요한 경우도 있겠지만 Id 만 필요한 경우도 있다.
                                // 이런 경우에는 필요한 칼럼만 가져오도록 해서 성능 이점을 보자.
//                                member.team.id // 꼭 필요한 칼럼만 가지고오자.
//                        )
//                )
//                .from(member)
//                .innerJoin(member.team, team)
//                .where(member.team.id.eq(teamId))
//                .fetch();
//    }

    // Group By 최적화하기
    // 일반적으로 MySQL 에서는 Group By 를 실행하면 GROUP BY column 에 의한 Filesort 라는 정렬 알고리즘이 추가적으로 실행된다.
    // Filesort 가 발생하면 상대적으로 더 느려지므로 이 경우를 막으려면 order by null 을 사용하면 된다.
    // 하지만 Querydsl 에서는 order by null 을 지원하지 않는다.
    // 그래서 OrderByNull 이라는 클래스를 만들고 이를 통해서 OrderByNull 을 지원하도록 해야한다.
    // 주의할 점은 OrderByNull 은 페이징 쿼리인 경우에는 사용하지 못한다.
    // 정렬의 경우에는 100건이 이하라면 애플리케이션 메모리로 가져와서 정렬 하는 걸 추천한다.
    // 왜냐하면 일반적으로 DB 자원보다는 애플리케이션 자원이 더 싸기 때문에 더 효율적이다.
    public List<Integer> useOrderByNull() {
        return queryFactory
                .select(member.age.sum())
                .from(member)
                .innerJoin(member.team, team)
                .groupBy(member.team)
                .orderBy(OrderByNull.DEFAULT)
                .fetch();
    }

    // Querydsl 에서 커버링 인덱스 사용하기
    // 커버링 인덱스는 쿼리를 충족시키는데 필요한 모든 칼럼을 가지고 있는 인덱스다.
    // select / where / order by / group by 등에서 사용되는 모든 칼럼이 인덱스에 포함된 상태로
    // No Offset 방식과 더불어 페이징 조회 성능을 향상시키는 가장 보편적인 방법
    // 즉 인덱스 검색으로 빠르게 처리하고 걸러진 항목에 대해서만 데이터 블록에 접근하기 때문에 성능의 이점을 얻게 된다.
    // 커버링 인덱스를 사용할땐 from 절에 subQuery 에서 커버링 인덱스를 통해 필터를 하도록 하는게 보편적인데
    // 아쉽게도 Querydsl 에선 JPQL은 from 절에서 서브쿼리를 지원하지 않는다.
    // 이를 위한 우회하는 방법으로는 두번의 select 절을 이용
    //    첫번째 select 절로 커버링 인덱스를 활용해 조회 대상의 PK를 조회한다.
    //    그 후 두번째 select 절로 해당 PK로 필요한 칼럼 항목들을 조회한다
    // 단점으로는 너무 많은 인덱스가 생길 수 있다는 점
    public List<MemberDto> useCoveringIndex(int offset, int limit){
        List<Long> ids = queryFactory
                .select(member.id)
                .from(member)
                .where(member.username.like("member%"))
                .orderBy(member.id.desc())
                .limit(limit)
                .offset(offset)
                .fetch();

        if(ids.isEmpty()){
            return new ArrayList<>();
        }

        return queryFactory
                .select(new QMemberDto(
                        member.username,
                        member.age
                ))
                .from(member)
                .where(member.id.in(ids))
                .orderBy(member.id.desc())
                .fetch();
    }

    // 페이징 성능 개선을 위해 No Offset 사용하기
    // 기존 페이징 방식인 offset 과 limit 를 이용한 방식은 서비스가 커짐에 따라서 장애를 유발할 수도 있다.
    // 이유로는 초기엔 데이터가 적어서 문제가 없지만 데이터가 점점 많아지면 느려지기 때문인데
    // 결국에는 offset 을 이용하면 offset + limit 만큼의 데이터를 읽어야 하기 때문이다.
    // 페이지 번호가 뒤로 갈수록 앞에서 읽었던 행을 다시 읽어야 한다.
    // 이 말은 offset이 10000이고 limit가 20이라면 10,020 행을 읽어야 한다는 것이고 그러고 나서 10,000 개의 행을 버리는 것이다.
    // No Offset 방식은 시작 지점을 인덱스로 빠르게 찾아 첫 페이지부터 읽도록 하는 방식
    // 이전에 조회된 결과를 한번에 건너뛸 수 있게 마지막 조회 결과의 ID 를 조건문에 사용하는 방식을 이용한다.
    public List<MemberDto> noOffset(Long lastMemberId, int limit){
        // SELECT *
        // FROM items
        // WHERE 조건문
        // AND id < 마지막 조회 ID
        // ORDER BY id desc
        // LIMIT 페이지 사이즈
        return queryFactory
                .select(new QMemberDto(
                        member.username,
                        member.age
                ))
                .from(member)
                .where(member.username.contains("member")
                        .and(memberIdLt(lastMemberId)))
                .orderBy(member.id.desc())
                .limit(limit)
                .fetch();
    }

    private BooleanExpression memberIdLt(Long lastMemberId) {
        return lastMemberId != null ? member.id.lt(lastMemberId): null;
    }

    // 일괄 Update 최적화하기
    // JPA를 사용하면 영속성 컨택스트가 Dirty Checking 기능을 지원해주는데 이를 이용하면 엄청나게 많은 양의 데이터에 대해서 업데이트 쿼리가 나갈수도 있다.
    // 주의할 점은 일괄 업데이트는 영속성 컨택스트의 1차 캐시 갱신이 안된다. 그러므로 Cache Eviction 이 필요하다
    // 그러모르 실시간 비즈니스 처리나 실시간 단건 처리가 필요하다면 Dirty Checking 기능을 본격적으로 이용하고
    // 대량량 데이터를 일괄 업데이트가 필요하면 위의 방식을 사용하자.
    // JPA Dirty Checking 을 이용한 예제
    private void dirtyChecking(){
        List<Member> result = queryFactory
                .selectFrom(member)
                .fetch();

        for (Member member : result){
            member.setUsername(member.getUsername() + "+");
        }
    }
    // Querydsl 일괄 업데이트를 이용한 예제
    public void batchUpdate(){
        queryFactory
                .update(member)
                .set(member.username, member.username + "+")
                .execute();
    }

    // JPA로 Bulk Insert는 자제하자.
    // JDBC에는 rewriteBatchedStatements 로 Insert 합치기 라는 옵션이 있다. 이를 통해 여러 Insert 문을 하나의 Insert 로 작업하도록 하는 것을 말한다.
    /*
        INSERT INTO message (`content`, `status`, `created_by`, `created_at`,`last_modified_at`)
        VALUES (:content, :status, :created_by, :created_at, :last_modified_at);
        INSERT INTO message (`content`, `status`, `created_by`, `created_at`,`last_modified_at`)
        VALUES (:content, :status, :created_by, :created_at, :last_modified_at);
        INSERT INTO message (`content`, `status`, `created_by`, `created_at`,`last_modified_at`)
        VALUES (:content, :status, :created_by, :created_at, :last_modified_at);

        -->

        INSERT INTO message (`content`, `status`, `created_by`, `created_at`,`last_modified_at`)
        VALUES (:content, :status, :created_by, :created_at, :last_modified_at)
        , (:content, :status, :created_by, :created_at, :last_modified_at)
        , (:content, :status, :created_by, :created_at, :last_modified_at)
     */
    // 하지만 JPA 에는 auto_increment일때 insert 합치기가 적용되지 않는다. 그러므로 이 기능이 필요하다면 JdbcTemplate 를 사용하자.
}
