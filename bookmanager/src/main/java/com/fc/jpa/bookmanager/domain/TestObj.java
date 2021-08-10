package com.fc.jpa.bookmanager.domain;

import com.fc.jpa.bookmanager.domain.listener.TestListener;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TestObj extends TestListener {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "test_id")
    @ToString.Exclude
    private TestEntity testEntity;
}
