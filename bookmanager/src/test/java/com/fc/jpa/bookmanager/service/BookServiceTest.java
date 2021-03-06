package com.fc.jpa.bookmanager.service;

import com.fc.jpa.bookmanager.domain.Book;
import com.fc.jpa.bookmanager.repository.AuthorRepository;
import com.fc.jpa.bookmanager.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorService authorService;

    @Test
    void transactionTest() {

        try {
//            bookService.put();
            bookService.putTest();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(bookRepository.findAll());
        System.out.println("==================================");
        System.out.println(authorRepository.findAll());
    }

// ## 트랜잭션
// 원자성, 일관성, 독립성, 영속성 (ACID)
//  # Atomicity(원자성)
//      - 모든 작업이 반영되거나 모두 롤백되는 특성입니다
//  # Consistency(일관성)
//      - 데이터는 미리 정의된 규칙에서만 수정이 가능한 특성을 의미합니다. 숫자컬럼에 문자열값을 저장이 안되도록 보장해줍니다.
//  # Isolation(고립성)
//      - A와 B 두개의 트랜젝션이 실행되고 있을 때, A의 작업들이 B에게 보여지는 정도를 의미합니다. (다른 트랜잭션으로부터의 영향)
//  # Durability(영구성)
//      - 한번 반영(커밋)된 트랜젝션의 내용은 영원히 적용되는 특성을 의미합니다.


// 트랜잭션 격리 단계 : 동시에 여러 트랜잭션이 처리될 때
//                   특정 트랜잭션이 다른 트랜잭션에서 변경하거나 조회하는 데이터를 볼 수 있도록 허용할지 말지 정도를 결정하는 것.
// isolation
// DEFAULT : DB의 기본 격리단계
// # 데이터 무결성(Data Integrity) : 데이터 값이 정확한 상태
// # 데이터 정합성 : 어떤 데이터들이 값이 서로 일치함. (정확한 정보 제공)
// 순서대로 격리 단계가 강함 -> 정합성이 보장 (동시 처리 성능은 떨어진다)
// # READ UNCOMMITTED : 커밋되지 않은 데이터에 접근이 가능한 수준(dirty read)으로, N개의 트랜잭션이 하나의 공유 데이터에 접근해도 전혀 보호되지 않는다. (사용하지 않는 것을 권장)
// # READ COMMITTED : 커밋된 데이터만 조회 (unRepeatable read 현상 발생함)
// # REPEATABLE READ : 트랜잭션이 시작되고 종료되기 전까지 한 번 조회한 값은 계속 같은 값이 조회되는 격리 수준이다.
//                   트랜잭션 시작 전에 커밋된 내용에 한해서만 조회된다. (트랜잭션 내에서 반복해서 조회해도 항상 같은 값이 조회되도록 보장)
//                   다른 트랜잭션의 커밋된 값을 가져오지 않음 (스냅샷을 사용)
//                   단점으로 팬텀 리드라는게 존재 (다른 트랜잭션에서 수행한 변경 작업에 의해 레코드가 보였다가 안 보였다가 하는 현상
//                                              이를 방지하기 위해서는 쓰기 잠금을 걸어야 한다.)
// # SERIALIZABLE : 트랜잭션이 특정 테이블을 읽으면 다른 트랜잭션은 그 테이블의 데이터를 추가/변경/삭제할 수 없다.
//                데이터에 접근할때 다른 트랜잭션에서 데이터를 변경하려 하면 다른쪽 트랜잭션이 끝날때 까지 기다린다.
//                가장 강력한 격리 수준이며 데이터 정합성을 가장 잘 보장한다.
//                그러나 동시 처리 성능이 가장 떨어진다. ( 거의 사용되지 않는다. )

    @Test
    void isolationTest() {
        Book book = new Book();
        book.setName("JPA 강의");
        bookRepository.save(book);

        bookService.get(1L);
        System.out.println(" >>> " + bookRepository.findAll());
    }

    // 트랜잭션의 전파 (propagation)
    // JPA 서 트랜잭션의 시작과 끝은 각 메소드의 처음과 끝
    // 현재의 트랜잭션과 다른 클래스의 트랜잭션간의 처리에서 교통정리를 해주는 속성

    // # REQUIRED (기본값) : 기본 속성이다. 미리 시작한 트랜잭션이 있으면 참여하고 아니면 시작
    // # REQUIRES_NEW : 진행 중인 트랜잭션이 있으면 잠시 보류시키고 항상 새로운 트랜잭션을 시작 (자체적으로 트랜잭션 진행)
    // # MANDATORY : 부모 트랜잭션에 합류합니다. 만약 부모 트랜잭션이 없다면 예외를 발생 (이미 트랜잭션이 존재해야함)
    // # NESTED : 이미 진행 중인 트랜잭션이 있으면 중첩 트랜잭션을 시작 (트랜잭션 안에 다시 트랜잭션을 만든다, 부모에 영향을 주지 않고 반대로 영향을 받음, 기본적으론 사용안함)
    // # NEVER : 트랜잭션을 생성하지 않습니다. 부모 트랜잭션이 존재한다면 예외를 발생 (트랜잭션이 없어야함)
    // # SUPPORTS : 부모 트랜잭션이 있다면 합류합니다. 진행중인 부모 트랜잭션이 없다면 트랜잭션을 생성하지 않습니다.
    // # NOT_SUPPORTED : 부모 트랜잭션이 있다면 보류시킵니다. 진행중인 부모 트랜잭션이 없다면 트랜잭션을 생성하지 않습니다.

    @Test
    void converterErrorTest() {
        System.out.println("============================================");
        bookService.getAll();
        System.out.println("============================================");

//        bookRepository.findAll().forEach(System.out::println);
    }
}