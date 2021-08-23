package com.example.querytest.domain;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.CaseBuilder;
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

import static com.querydsl.core.types.dsl.Expressions.constant;
import static org.assertj.core.api.Assertions.assertThat;
import static com.example.querytest.domain.QMember.*;
import static com.example.querytest.domain.QTeam.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
//@Commit
public class MemberTest {

    @Autowired
    EntityManager em;

    // JPAQueryFactory 말고 JPQLQuery 를 사용해서 쿼리를 만들 수도 있다. (board 참고)
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

    @Test
    public void startQuerydsl() {
//        QMember qMember = new QMember("m"); // 별칭 직접 사용
//        QMember qMember = QMember.member;  // 기본 인스턴스 사용
        // 같은 테이블을 조인하는 경우가 아니면 기본 인스턴스를 사용
        QMember m = new QMember("m");

        Member findMember = queryFactory
                .select(m)
                .from(m)
                .where(m.username.eq("member1")) // username = "member1"
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void startQuerydsl2() {
        // using static import
        Member findMember = queryFactory
                .select(member)
                .from(member)
                .where(member.username.eq("member1"))
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    // 검색 조건 쿼리
    @Test
    public void search() {
        Member findMember = queryFactory
                .selectFrom(member)
                // 검색 조건은 .and(), .or()를 메서드 체인으로 연결할 수 있다.
                // 이외에도 .eq, .ne, .isNotNull, .in, .notIn, .between, .goe, .loe, .gt, .lt, .like, .contains, .startsWith 등이 있다.
                .where(member.username.eq("member1")
                        .and(member.age.eq(10)))
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    public void search2() {
        Member findMember = queryFactory
                .selectFrom(member)
                // and 조건을 파라미터로 처리
                .where(
                        member.username.eq("member1"),
                        member.age.eq(10))
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    // 정렬 쿼리
    @Test
    public void sort() {
        // asc(), desc() : 일반 정렬
        // nullsLast(), nullsFirst() : null 데이터 순서 부여
        // nullsLast() ( null 인 항목을 마지막으로)와 nullsFirst() ( null 인 항목을 처음으로)
        List<Member> result = queryFactory
                .selectFrom(member)
                .where(member.age.eq(100))
                .orderBy(member.age.desc(), member.username.asc().nullsLast())
                .fetch();

        Member member5 = result.get(0);
        Member member6 = result.get(1);
        Member memberNull = result.get(2);

        assertThat(member5.getUsername()).isEqualTo("member5");
        assertThat(member5.getUsername()).isEqualTo("member6");
        assertThat(member5.getUsername()).isNull();
    }

    // 페이징 쿼리
    @Test
    public void paging1() {
//        List<Member> result = queryFactory
//                .selectFrom(member)
//                .orderBy(member.username.desc())
//                .offset(1) // 0부터 시작
//                .limit(2)  // 최대 2건 조회
//                .fetch();

        // 조회한 리스트 + 전체 개수를 포함한 QueryResults 반환
        // 전체 조회수가 필요하다면, fetch 대신 fetchResults를 사용한다.
        // 이때, count 쿼리가 실행되니 join을 한다면 성능상 주의를 해야한다
        QueryResults<Member> result2 = queryFactory
                .selectFrom(member)
                .join(member.team, team)
                .where(team.name.eq("teamA"))
                .orderBy(member.username.desc())
                .offset(0) // 0부터
                .limit(2)  // n건 조회
                .fetchResults();

        System.out.println("getTotal : " +  result2.getTotal());
        System.out.println("getLimit : " +  result2.getLimit());
        System.out.println("getOffset : " +  result2.getOffset());
        System.out.println("getResults : " +  result2.getResults());

//        assertThat(result.size()).isEqualTo(2);
    }

    // 집합
    @Test
    public void aggregation() throws Exception {

        List<Tuple> result = queryFactory
                .select(member.count(),
                        member.age.sum(),
                        member.age.avg(),
                        member.age.max(),
                        member.age.min())
                .from(member)
                .fetch();

        Tuple tuple = result.get(0);
        assertThat(tuple.get(member.count())).isEqualTo(4);
        assertThat(tuple.get(member.age.sum())).isEqualTo(100);
        assertThat(tuple.get(member.age.avg())).isEqualTo(25);
        assertThat(tuple.get(member.age.max())).isEqualTo(40);
        assertThat(tuple.get(member.age.min())).isEqualTo(10);
    }

    // 조인
    @Test
    public void join() throws Exception {
        QMember member = QMember.member;
        QTeam team = QTeam.team;

        // join(), innerJoin() : 내부 조인
        // leftJoin() : left 외부 조인
        // rightJoin() : right 외부 조인
        // JPQL의 on과 성능 최적화를 위한 fetch Join 제공
        // 조인의 기본 문법은 첫 번째 파라미터에 조인 대상을 지정하고, 두 번째 파라미터에 별칭으로 사용할 Q타입을 지정하면 된다.
        List<Member> result = queryFactory
                .selectFrom(member)
                .join(member.team, team)
                .where(team.name.eq("teamA"))
                .fetch();

        assertThat(result)
                .extracting("username")
                .containsExactly("member1", "member2");
    }

    @Test
    public void theta_join() throws Exception {
        // 조인에 참여하는 두 릴레이션의 속성 값을 비교하여 조건을 만족하는 투플만 반환
        QMember member = QMember.member;
        QTeam team = QTeam.team;

        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));
        // 연관관계가 없는 필드로 조인 (where 로 관계)
        List<Member> result = queryFactory
                .select(member)
                .from(member, team) // 조인에 참여하는 두 릴레이션 지정
                .where(member.username.eq(team.name)) // 속성 값을 비교
                .fetch();

        System.out.println(result);
        assertThat(result)
                .extracting("username")
                .containsExactly("teamA", "teamB");
    }

    @Test
    public void join_on_filtering() throws Exception {
        // on 절을 활용해서 조인 대상을 필터링할 때, 외부조인이 아니라 내부조인을 사용하면 where절에서 필터링하는 것과 동일하기 때문에
        // 내부조인이라면, where절로 하고, 외부 조인일 경우만 on을 활용하자.
        List<Tuple> result = queryFactory
                .select(member, team)
                .from(member)
                .leftJoin(member.team, team)
                    .on(team.name.eq("teamA"))
                .fetch();

        for (Tuple tuple : result) {
            System.out.println("tuple = " + tuple);
        }
    }

    @Test
    public void join_on_no_relation() throws Exception {

        em.persist(new Member("teamA"));
        em.persist(new Member("teamB"));
        // 연관관계 없는 엔터티 외부 조인
        List<Tuple> result = queryFactory
                .select(member, team)
                .from(member)
                .leftJoin(team) // leftJoin() 부분에 일반조인과 다르게 엔티티 하나만 들어간다.
                    .on(member.username.eq(team.name))
                .fetch();

        for (Tuple tuple : result) {
            System.out.println("t=" + tuple);
        }
    }

    @Test
    public void fetchJoinUse() throws Exception {

        em.flush();
        em.clear();

        // fetch join (join(), leftJoin() 뒤에 fetchJoin()이라고 추가)
        Member findMember = queryFactory
                .selectFrom(member)
                .join(member.team, team).fetchJoin()
                .where(member.username.eq("member1"))
                .fetchOne();

        System.out.println(findMember);

        // PersistenceUnitUtil.isLoaded(Object entity) 메소드를 사용하면 프록시 인스턴스의 초기화 여부를 확인할 수 있다.
        boolean isLoaded = emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam());
        assertEquals(true, isLoaded);
    }

    // 서브쿼리
    @Test
    public void subQuery() {

        //given
        QMember qMember = member;  // alias 가 중복되면 안되므로 QMember 를 만들어줘야 한다.

        //when
        Member findMember = queryFactory
                .selectFrom(member)
                .where(member.age.eq(
                        JPAExpressions
                                .select(qMember.age.max())
                                .from(qMember)

                ))
                .fetchOne();
        //then
        assertEquals(40, findMember.getAge());
        // JPA JPQL 서브 쿼리의 한계점으로 from 절의 서브쿼리는 지원하지 않는다. 당연히 querydsl도 지원하지 않는다

    }

    @Test
    void subQueryAvg(){
        //given
        QMember qMember = new QMember("m"); 

        //when
        List<Member> findMembers = queryFactory
                .selectFrom(member)
                .where(member.age.goe(
                        JPAExpressions
                                .select(qMember.age.avg())
                                .from(qMember)

                ))
                .fetch();
        //then
        assertEquals(2, findMembers.size());
    }

    @Test
    void subQueryIn(){
        //given
        QMember qMember = new QMember("m"); // alias 가 중복되면 안되므로 QMember 를 만들어줘야 한다.

        // in 사용
        //when
        List<Member> findMembers = queryFactory
                .selectFrom(member)
                .where(member.age.in(
                        JPAExpressions
                                .select(qMember.age)
                                .from(qMember)
                                .where(qMember.age.in(10))
                ))
                .fetch();
        //then
        assertEquals(1, findMembers.size());
        assertEquals(10, findMembers.get(0).getAge());
    }

    // Case 문
    @Test
    void baseCase(){
        //given

        //when
        List<String> result = queryFactory
                .select(member.age
                        .when(10).then("열살")
                        .when(20).then("스무살")
                        .otherwise("기타"))
                .from(member)
                .fetch();
        //then
        for (String s : result) {
            System.out.println(s);
        }
    }

    @Test
    void complexCase(){
        //given

        //when
        List<String> result = queryFactory
                // CaseBuilder() 를 이용
                .select(new CaseBuilder()
                        .when(member.age.between(0, 20)).then("0~20살")
                        .when(member.age.between(21, 30)).then("21~30살")
                        .otherwise("기타")
                )
                .from(member)
                .fetch();

        //then
        for (String s : result){
            System.out.println(s);
        }
    }


    @Test
    void complexCase2(){
        //given
        NumberExpression<Integer> rankCase = new CaseBuilder()
                .when(member.age.between(0, 20)).then(2)
                .when(member.age.between(21, 30)).then(1)
                .otherwise(3);
        //when
        List<Tuple> result = queryFactory
                .select(member.username, member.age, rankCase)
                .from(member)
                .orderBy(rankCase.desc()) // order By 에서도 사용 가능
                .fetch();
        //then
        for(Tuple tuple : result) {
            System.out.println(tuple);
        }
    }

    // 상수가 필요하다면 Expressions.constant() 를 사용하면 된다. 줄여서 쓰고 싶다면 static import 를 사용하자.
    @Test
    void addConstant(){
        //given
        //when
        List<Tuple> result = queryFactory
                .select(member.username, constant("A"))
                .from(member)
                .fetch();

        //then
        for (Tuple tuple : result) {
            System.out.println(tuple);
        }
    }

    // 문자를 더할거면 concat 을 이용하면 편하다.
    @Test
    void addConcat(){
        //given

        //when
        String result = queryFactory
                .select(member.username.concat("_").concat(member.age.stringValue()))
                .from(member)
                .where(member.username.eq("member1"))
                .fetchOne();
        //then
        System.out.println(result);
    }
}
/*
    # 결과 조회
    fetch() : 리스트 조회, 데이터가 없으면 빈 리스트 반환
    fetchOne() : 단 건 조회, 결과가 없으면, null을 반환하고 둘 이상이면 예외를 발생시킨다.
    fetchFirst() : limit(1).fetchOne()
    fetchResults() : 페이징 정보 포함, total count 쿼리 추가 실행한다.
    fetchCount() : count쿼리로 변경해서 count 수를 조회한다.
 */