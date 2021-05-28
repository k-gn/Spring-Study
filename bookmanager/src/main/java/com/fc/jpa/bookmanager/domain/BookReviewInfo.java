package com.fc.jpa.bookmanager.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true) // (callSuper = true) : 부모 필드도 감안할 지 여부 체크
@EqualsAndHashCode(callSuper = true)
public class BookReviewInfo extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Long bookId;

    private float averageReviewScore;

    private int reviewCount;

}
