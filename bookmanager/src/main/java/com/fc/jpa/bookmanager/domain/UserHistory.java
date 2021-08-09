package com.fc.jpa.bookmanager.domain;

import com.fc.jpa.bookmanager.domain.listener.Auditable;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Martin
 * @since 2021/03/31
 */
@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
//@EntityListeners(value = AuditingEntityListener.class)
public class UserHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "user_id", insertable = false, updatable = false), onetomany 쪽에서 지정한 joincolumn 명과 같아야함
//    private Long userId;

    private String name;

    private String email;

    @ManyToOne // many 쪽에 자동으로 fk를 만들어준다.
    @ToString.Exclude
    private User user;

//    @CreatedDate
//    private LocalDateTime createdAt;
//
//    @LastModifiedDate
//    private LocalDateTime updatedAt;
}