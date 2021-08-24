package com.example.querytest.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

// 동적 쿼리와 성능 최적화 조회 - Builder 사용
// 동적 쿼리를 사용하기 위해 조건들을 Builder 로 만들고 이를 한번에 Dto 로 가지고 오도록 해서 성능을 최적화 하는 방법
@Data
public class MemberTeamDto {
    private Long memberId;
    private String username;
    private int age;
    private Long teamId;
    private String teamName;

    @QueryProjection
    public MemberTeamDto(Long memberId, String username, int age, Long teamId, String teamName) {
        this.memberId = memberId;
        this.username = username;
        this.age = age;
        this.teamId = teamId;
        this.teamName = teamName;
    }
}
