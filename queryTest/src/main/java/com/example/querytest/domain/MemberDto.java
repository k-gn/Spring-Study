package com.example.querytest.domain;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class MemberDto {

    private String username;
    private int age;

    public MemberDto(){
    }

    @QueryProjection // 생성자에 @QueryProjection 이 붙는다. 이후 빌드 툴을 이용해 compile 하면 Q타입의 클래스가 생성된다.
    public MemberDto(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
