package com.fc.jpa.bookmanager.domain;
import java.time.LocalDateTime;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import com.fc.jpa.bookmanager.domain.listener.Auditable;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

// 중복 제거를 위해 추상화한 클래스
@Data
@MappedSuperclass // 해당 클래스의 필드를 상속받는 엔티티 컬럼으로 포함
// 등록 및 수정일 등록같은 날짜 관련된 리스너는 워낙 자주 쓰여서 스프링에서 별도의 기본 리스너를 제공하고 있다.
@EntityListeners(value = AuditingEntityListener.class) // 이미 스프링에서 만들어 놓은 엔티티 리스너를 사용 (Auditing : 감시)
public class BaseEntity implements Auditable {

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}