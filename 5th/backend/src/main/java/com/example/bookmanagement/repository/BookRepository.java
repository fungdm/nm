package com.example.bookmanagement.repository;

import com.example.bookmanagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Query methods based on method names
    List<Book> findByTitle(String title);

    List<Book> findByAuthor(String author);

    List<Book> findByPublicationYear(int publicationYear);

    List<Book> findByTitleAndAuthor(String title, String author);
}
