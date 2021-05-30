package com.fc.jpa.bookmanager.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true) // (callSuper = true) : 부모 필드도 감안할 지 여부 체크
@EqualsAndHashCode(callSuper = true)
public class BookReviewInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private Long bookId;

    private float averageReviewScore;

    private int reviewCount;

    // @OneToOne 옵션에서 optional=false 로 객체가 null 인경우를 허용하지 않도록 해두면 조인시 inner join 으로 동작되고
    // optional=true 로하면 left outer join 으로 동작
    @OneToOne(optional = false) // 1 대 1 연관관계 매핑, optional : 존재 여부
    private Book book;

}
