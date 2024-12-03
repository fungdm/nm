package com.example.bookmanagement.service;

import com.example.bookmanagement.model.Book;
import com.example.bookmanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getBooksByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> getBooksByPublicationYear(int year) {
        return bookRepository.findByPublicationYear(year);
    }

    public List<Book> getBooksByTitleAndAuthor(String title, String author) {
        return bookRepository.findByTitleAndAuthor(title, author);
    }
}
