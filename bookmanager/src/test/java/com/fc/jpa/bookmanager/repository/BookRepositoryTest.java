package com.fc.jpa.bookmanager.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.fc.jpa.bookmanager.domain.Book;
import com.fc.jpa.bookmanager.domain.Publisher;
import com.fc.jpa.bookmanager.domain.Review;
import com.fc.jpa.bookmanager.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;
    @Test

    void bookTest() {
        Book book = new Book();
        book.setName("Jpa 초격차 패키지");
//        book.setPublisherId(1L);
//        book.setAuthorId(1L);

        bookRepository.save(book);

        System.out.println(bookRepository.findAll());

    }

    @Test
    @Transactional
    void bookRelationTest() {
        givenBookAndReview();

        User user = userRepository.findByEmail("martin@fastcampus.com");

//        System.out.println("Review : " + user.getReviews());
//        System.out.println("Book : " + user.getReviews().get(0).getBook());
//        System.out.println("Publisher : " + user.getReviews().get(0).getBook().getPublisher());
    }


    @Test
//    @Transactional
    public void cascade() {
        Publisher publisher = new Publisher();
        publisher.setName("FastCampus");
//        publisherRepository.save(publisher);

        System.out.println("==================================================1");

        Book book = new Book();
        book.setName("JPA CASCADE");
        book.setPublisher(publisher); // 참조해야 연관관계 설정된다.
        publisher.getBooks().add(book);  // 객체지향적으로 양방향으로 참조하는게 좋다.
        bookRepository.save(book);


        System.out.println("====================================================2");
        System.out.println("books : " + bookRepository.findAll());
        System.out.println("====================================================3");
        System.out.println("publishers : " + publisherRepository.findAll());
        System.out.println("====================================================3");

//        Book book1 = bookRepository.findById(1L).get();
//        book1.getPublisher().setName("SLOW");
//        bookRepository.save(book1);
//        // book을 통해서 수정해서 현재 book에 merge cascade가 없으면 수정이 안된다.
//        System.out.println("publishers : " + publisherRepository.findAll());

        System.out.println("====================================================3");

//        Book book2 = bookRepository.findById(1L).get();
//        bookRepository.delete(book2);
//        System.out.println("books : " + bookRepository.findAll());
//        System.out.println("publishers : " + publisherRepository.findAll());

        System.out.println("====================================================3");

        Book book3 = bookRepository.findById(1L).get();
        book3.setPublisher(null);
        bookRepository.save(book3);

        System.out.println("books : " + bookRepository.findAll());
        System.out.println("publishers : " + publisherRepository.findAll());
    }

    @Test
    void remove() {

        bookRepository.deleteById(1L);

        System.out.println(bookRepository.findAll());
        System.out.println("====================================================");
        System.out.println(publisherRepository.findAll());
    }

    @Test
    void softDelete() {
        bookRepository.findAll().forEach(System.out::println);
//        bookRepository.findByCategoryIsNull().forEach(System.out::println);
//        bookRepository.findByDeletedFalse().forEach(System.out::println);
    }

    @Test
    void queryTest() {
        bookRepository.findAll().forEach(System.out::println);

        System.out.println(bookRepository.findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual(
                "JPA 초격차 패키지",
                LocalDateTime.now().minusDays(1L),
                LocalDateTime.now().minusDays(1L)
        ));
    }

    private void givenBookAndReview() {
        givenReview(givenUser(), givenBook(givenPublisher()));
    }

    private User givenUser() {
        return userRepository.findByEmail("martin@fastcampus.com");
    }

    private void givenReview(User user, Book book) {
        Review review = new Review();
        review.setTitle("내 인생을 바꾼 책");
        review.setContent("너무너무 재미있고 즐거운 책이었어요.");
        review.setScore(5.0f);
        review.setUser(user);
        review.setBook(book);

        reviewRepository.save(review);
    }

    private Book givenBook(Publisher publisher) {
        Book book = new Book();
        book.setName("JPA 초격차 패키지");
        book.setPublisher(publisher);

        return bookRepository.save(book);
    }

    private Publisher givenPublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("패스트캠퍼스");

        return publisherRepository.save(publisher);
    }
}
