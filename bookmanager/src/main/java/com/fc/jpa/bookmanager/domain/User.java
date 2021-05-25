package com.fc.jpa.bookmanager.domain;

import java.time.LocalDateTime;
import java.util.List;

import com.fc.jpa.bookmanager.domain.listener.Auditable;
import com.fc.jpa.bookmanager.domain.listener.UserEntityListener;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor // NonNull or final, 다른 consturctor 어노테이션 존재 시 @Data 있어도 작성해줘야함
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder // 빌더 형식으로 객체를 생성하고 필드값을 주입해준다.
@Entity // 자바 객체를 엔티티로 선언
// @Table 엔티티와 매핑할 테이블을 지정, 인덱스나 제약조건 등을 지정해줄 수 있다.
//@Table(name = "user", indexes = { @Index(columnList = "name")}, uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
@EntityListeners(value = { UserEntityListener.class })
public class User extends BaseEntity implements Auditable {

    @Id
    // identity : mysql에서 일반적으로 많이 쓴다. (auto increment)
    // sequence : oracle에서 일반적으로 많이 쓴다. (or postgre)
    // table : db 종류에 상관없이 별도에 table을 만들어 사용
    // auto : default (db에 적합한 값을 자동으로 설정)
    @GeneratedValue // 자동 증가값
    private Long id;

    @NonNull
    private String name;

    @NonNull
//    @Column(unique = true)
    private String email;

    @Enumerated(value = EnumType.STRING) // Enum 설정, 반드시 EnumType.String으로 쓰자.
    private Gender gender;

//    @Column(name = "crtdat", nullable = false, updatable = false) // 컬럼 매핑
//    @CreatedDate
//    private LocalDateTime createdAt;

//    @Column(insertable = false)
//    @LastModifiedDate
//    private LocalDateTime updatedAt;


    // Listener 
//    @PrePersist // insert 전
//    public void prePersist() {
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
//    }
//
//    @PreUpdate // update 전
//    public void preUpdate() {
//        this.updatedAt = LocalDateTime.now();
//    }
}
