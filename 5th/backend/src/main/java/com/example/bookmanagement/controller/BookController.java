package com.example.bookmanagement.controller;

import com.example.bookmanagement.model.Book;
import com.example.bookmanagement.repository.BookRepository;
import com.example.bookmanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/books")

public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/title/{title}")
    public List<Book> getBooksByTitle(@PathVariable String title) {
        return bookService.getBooksByTitle(title);
    }

    @GetMapping("/author/{author}")
    public List<Book> getBooksByAuthor(@PathVariable String author) {
        return bookService.getBooksByAuthor(author);
    }

    @GetMapping("/year/{year}")
    public List<Book> getBooksByYear(@PathVariable int year) {
        return bookService.getBooksByPublicationYear(year);
    }

    @GetMapping("/title-author")
    public List<Book> getBooksByTitleAndAuthor(@RequestParam String title, @RequestParam String author) {
        return bookService.getBooksByTitleAndAuthor(title, author);
    }
}
