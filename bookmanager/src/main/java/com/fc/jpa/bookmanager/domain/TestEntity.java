package com.fc.jpa.bookmanager.domain;

import com.fc.jpa.bookmanager.domain.listener.TestListener;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
 # h2 database
 - 자바 기반 경량화 DB
 - 파일로 저장해서 실제 DB 처럼 데이터를 유지할수도 있고, 메모리 DB로 사용해서 실제 인스턴스가 동작하는 시점에만 유지하기도 한다.
 - 프로젝트 초기에 테스트 DB로 사용도 하고, 유지보수 진행 시 Junit 테스트용 db로 사용할수도 있다.
*/

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TestEntity extends TestListener {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "testEntity", fetch = FetchType.EAGER)
//    @JoinColumn(name = "test_id")
//    @ToString.Exclude
    private List<TestObj> testList = new ArrayList<>();;
}
