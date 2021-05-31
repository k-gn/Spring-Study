package com.fc.jpa.bookmanager.domain;
import com.fc.jpa.bookmanager.domain.listener.Auditable;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
//@EntityListeners(value = AuditingEntityListener.class)
public class Book extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

//    private Long authorId;

//    @Column(name = "publisher_id")
//    private Long publisherId;

    // JPA에서는 두 객체 연관관계 중 하나를 정해서 테이블의 외래키를 관리해야 하는데 이것을 연관관계의 주인이라고 한다.
    // 주인이 아니면 mappedBy 속성을 사용해서 속성의 값으로 연관관계의 주인을 지정
    // 연관관계의 주인은 외래 키가 있는 곳으로 설정
    @OneToOne(mappedBy = "book") // mappedBy : 양방향 매핑일 때 사용, 반대쪽 매핑의 필드 이름을 값, 해당 테이블에서 연관키가 사라진다.
    @ToString.Exclude // ToString 에서 제외 : 엔티티 릴레이션에서 순환참조를 없애기
    private BookReviewInfo bookReviewInfo;

    // One 쪽의 PK 를 Many 쪽에서 FK 로 가지고 있다.
    @OneToMany
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne
    private Publisher publisher;

    //    @ManyToMany
    @OneToMany
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private List<BookAndAuthor> bookAndAuthors = new ArrayList<>();

    public void addBookAndAuthors(BookAndAuthor... bookAndAuthors) {
        Collections.addAll(this.bookAndAuthors, bookAndAuthors);
    }

//    @CreatedDate
//    private LocalDateTime createdAt;
//
//    @LastModifiedDate
//    private LocalDateTime updatedAt;

//    @PrePersist
//    public void prePersist() {
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    public void preUpdate() {
//        this.updatedAt = LocalDateTime.now();
//    }
}
