package com.fc.jpa.bookmanager.repository;

import com.fc.jpa.bookmanager.domain.Author;
import com.fc.jpa.bookmanager.domain.BookAndAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookAndAuthorRepository extends JpaRepository<BookAndAuthor, Long> {

}
