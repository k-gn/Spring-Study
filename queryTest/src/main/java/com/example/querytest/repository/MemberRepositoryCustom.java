package com.example.querytest.repository;

import com.example.querytest.domain.MemberSearchCondition;
import com.example.querytest.dto.MemberTeamDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberRepositoryCustom {
    List<MemberTeamDto> search(MemberSearchCondition condition);

    // Querydsl 페이징 연동
    Page<MemberTeamDto> searchPageSimple(MemberSearchCondition condition, Pageable pageable); // 새로 추가한 페이징 메소드 1
    Page<MemberTeamDto> searchPageComplex(MemberSearchCondition condition, Pageable pageable); // 새로 추가한 페이징 메소드 2
}