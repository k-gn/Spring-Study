package com.fc.jpa.bookmanager.service;

import com.fc.jpa.bookmanager.domain.Author;
import com.fc.jpa.bookmanager.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void put() {
        Author author = new Author();
        author.setName("martin");
        authorRepository.save(author);
    }
}
