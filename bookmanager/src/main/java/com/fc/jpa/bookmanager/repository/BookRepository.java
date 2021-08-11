package com.fc.jpa.bookmanager.repository;

import com.fc.jpa.bookmanager.domain.Book;
import com.fc.jpa.bookmanager.domain.BookNameAndCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    // 커스텀 쿼리
    @Modifying // 어노테이션으로 작성된 수정, 삭제 쿼리 메소드를 사용(조회 쿼리를 제외하고 데이터에 변경)할 때 필요
    @Query(value = "update book set category = 'none'", nativeQuery = true)
    void update();

    List<Book> findByCategoryIsNull();

    List<Book> findByDeletedFalse();

    List<Book> findByCategoryIsNullAndDeletedFalse();

    List<Book> findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual(String name, LocalDateTime createdAt, LocalDateTime updatedAt);

//    @Query(value = "select b from Book b "
//            + "where name = :name and createdAt >= :createdAt and updatedAt >= :updatedAt and category is null")
//    List<Book> findByNameRecently(
//            @Param("name") String name,
//            @Param("createdAt") LocalDateTime createdAt,
//            @Param("updatedAt") LocalDateTime updatedAt);

//    @Query(value = "select new com.fastcampus.jpa.bookmanager.repository.dto.BookNameAndCategory(b.name, b.category) from Book b")
//    List<BookNameAndCategory> findBookNameAndCategory();
//
//    @Query(value = "select new com.fastcampus.jpa.bookmanager.repository.dto.BookNameAndCategory(b.name, b.category) from Book b")
//    Page<BookNameAndCategory> findBookNameAndCategory(Pageable pageable);

}
