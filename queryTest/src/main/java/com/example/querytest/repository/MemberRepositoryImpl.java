package com.example.querytest.repository;

import com.example.querytest.domain.Member;
import com.example.querytest.domain.MemberSearchCondition;
import com.example.querytest.dto.MemberTeamDto;
import com.example.querytest.dto.QMemberTeamDto;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;
import static com.example.querytest.domain.QMember.*;
import static com.example.querytest.domain.QTeam.*;

// 만약 Querydsl 을 이용하는 기능이 너무 특화된 기능이라면 굳이 MemberRepository 로 상속하도록 하는게 아니라
// 별도의 클래스를 만들고 거기서 쿼리를 관리하는 것도 좋다.
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<MemberTeamDto> search(MemberSearchCondition condition) {
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

    // 전체 카운트를 한번에 조회하는 단순한 방법 - searchPageSimple()
    @Override
    public Page<MemberTeamDto> searchPageSimple(MemberSearchCondition condition, Pageable pageable) {
        QueryResults<MemberTeamDto> results = queryFactory
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
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults(); // fetchResults 로 결과를 조회하면 count 쿼리와 결과를 가져오는 select 쿼리가 한번에 나간다.

        List<MemberTeamDto> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }

    // 데이터와 전체 카운트를 별도로 조회하는 방법 - searchPageComplex()
    @Override
    public Page<MemberTeamDto> searchPageComplex(MemberSearchCondition condition, Pageable pageable) {
        // 데이터를 가지고 오는 쿼리와 count 를 가지고 오는 쿼리를 별도로 작성해서 이후에 조회 후 합치는 방식이다.
        // 이걸 사용하는 이유는 count 쿼리의 경우에는 조인을 탈 필요가 없는 경우도 있기 떄문에 별도로 작성하는게 성능을 높일수도 있다.
        List<MemberTeamDto> content = queryFactory
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
                .offset(pageable.getOffset()) // offset 은 pageable 객체에 들어오는 page 와 size 값을 사용해 자동으로 구해진다.
                .limit(pageable.getPageSize())
                .fetch();

//        long total = queryFactory
//                .selectFrom(member)
//                .leftJoin(member.team, team)
//                .where(
//                        usernameEq(condition.getUsername()),
//                        teamNameEq(condition.getTeamName()),
//                        ageGoe(condition.getAgeGoe()),
//                        ageLoe(condition.getAgeLoe())
//                )
//                .fetchCount();
//        return new PageImpl<>(content, pageable, total);

        // Count 쿼리가 생략 가능한 경우가 있고 이를 스프링 데이터 JPA 에서 지원해주기도 한다.
        //  - 페이지 시작하면서 컨텐츠 사이즈가 페이지 사이즈 보다 작을때
        //  - 마지막 페이지인 경우에는 Count 쿼리를 쓸 필요가 없다. (offset + pageSize 를 하면 total 이 나오므로)
        // PageableExecutionUtils 유틸 클래스의 getPage 메서드 사용함으로써 경우에 따라 Count 쿼리를 생략
        JPAQuery<Member> countQuery = queryFactory
                .selectFrom(member)
                .leftJoin(member.team, team)
                .where(
                        usernameEq(condition.getUsername()),
                        teamNameEq(condition.getTeamName()),
                        ageGoe(condition.getAgeGoe()),
                        ageLoe(condition.getAgeLoe())
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }
}