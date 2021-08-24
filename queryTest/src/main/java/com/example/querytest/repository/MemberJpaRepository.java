package com.example.querytest.repository;

import com.example.querytest.domain.Member;
import com.example.querytest.domain.MemberSearchCondition;
import com.example.querytest.dto.MemberTeamDto;
import com.example.querytest.dto.QMemberTeamDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.hibernate.annotations.Where;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import static com.example.querytest.domain.QMember.member;
import static com.example.querytest.domain.QTeam.team;
import static org.springframework.util.StringUtils.hasText;

// 순수 JPA 레파지토리와 Querydsl
// 순수 JPA 레파지토리를 이용해서 사용하는 방법과 Querydsl 을 이용해서 사용하는 방법
@Repository
public class MemberJpaRepository {

    // # 순수 JPA 레파지토리
//    private final EntityManager em;
//
//    public MemberJpaRepository(EntityManager em) {
//        this.em = em;
//    }
//
//    public void save(Member member) {
//        em.persist(member);
//    }
//
//    public Optional<Member> findById(Long id) {
//        Member member = em.find(Member.class, id);
//        return Optional.ofNullable(member);
//    }
//
//    public List<Member> findAll(){
//        return em.createQuery("select m from Member m", Member.class).getResultList();
//    }
//
//    public List<Member> findByUsername(String username) {
//        return em.createQuery("select m from Member m where m.username =:username", Member.class)
//                .setParameter("username", username)
//                .getResultList();
//    }

    // # Querydsl
    // JpaQueryFactory 는 EntityManager 를 이용해서 생성해도 되고 빈으로 등록한 후 주입받아도 좋다.
    // 확실히 JpaQueryFactory 를 이용해서 작성하는게 더 타입 세이프하고 간단하다.
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public MemberJpaRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public void save(Member member) {
        em.persist(member);
    }

    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public List<Member> findAll_Querydsl(){
        return queryFactory
                .selectFrom(member)
                .fetch();
    }

    public List<Member> findByUsername_Querydsl(String username){
        return queryFactory
                .selectFrom(member)
                .where(member.username.eq(username))
                .fetch();
    }

    // 동적 쿼리와 성능 최적화 조회 - Builder 사용
    public List<MemberTeamDto> searchByBuilder(MemberSearchCondition condition){
        BooleanBuilder builder = new BooleanBuilder();

        // String 의 경우에는 null 체크도 있지만 "" 으로 들어올 수도 있기 때문에 StringUtils.hasText() 라는 라이브러리를 이용했다.
        // 동적 쿼리를 이용할 때는 여기에다가 limit 나 페이징 쿼리 또는 기본 조건을 추가해 대량의 데이터를 가지고 오지 않도록 설계가 나름 필요하다.
        if (hasText(condition.getUsername())) {
            builder.and(member.username.eq(condition.getUsername()));
        }

        if(hasText(condition.getTeamName())){
            builder.and(team.name.eq(condition.getTeamName()));
        }

        if(condition.getAgeGoe() != null) {
            builder.and(member.age.goe(condition.getAgeGoe()));
        }

        if(condition.getAgeLoe() != null){
            builder.and(member.age.loe(condition.getAgeLoe()));
        }

        return queryFactory
                .select(new QMemberTeamDto(
                        member.id.as("memberId"),
                        member.username,
                        member.age,
                        team.id.as("teamId"),
                        team.name.as("teamName")
                ))
                .from(member)
                .leftJoin(member.team, team)
                .where(builder)
                .fetch();
    }

    // 동적 쿼리와 성능 최적화 조회 - Where 절 파라미터 사용
    // BooleanExpression 는 Predicate 를 상속받고 있기도 하고 같은 BooleanExpression 끼리 조합(and)할 수 있어서 활용성이 더 좋다.
    public List<MemberTeamDto> searchByWhere(MemberSearchCondition condition){
        return queryFactory
                .select(new QMemberTeamDto(
                        member.id.as("memberId"),
                        member.username,
                        member.age,
                        team.id.as("teamId"),
                        team.name.as("teamName")
                ))
                .from(member)
                .leftJoin(member.team, team)
                .where(
                        usernameEq(condition.getUsername()),
                        teamNameEq(condition.getTeamName()),
                        ageGoe(condition.getAgeGoe()),
                        ageLoe(condition.getAgeLoe())
                )
                .fetch();
    }

    // 확실히 Builder 를 사용한 것 보다 Where 절을 파라미터 형식으로 조건을 거는게 좀 더 가독성이 좋다.
    private BooleanExpression usernameEq(String username) {
        return hasText(username) ? member.username.eq(username) : null;
    }

    private BooleanExpression teamNameEq(String teamName) {
        return hasText(teamName) ? team.name.eq(teamName) : null;
    }

    private BooleanExpression ageGoe(Integer ageGoe) {
        return ageGoe != null ? member.age.goe(ageGoe) : null;
    }

    private BooleanExpression ageLoe(Integer ageLoe) {
        return ageLoe != null ? member.age.loe(ageLoe) : null;
    }

    private BooleanExpression ageBetween(Integer ageLoe, Integer ageGoe) {
        return ageLoe(ageLoe).and(ageGoe(ageGoe));
    }
}