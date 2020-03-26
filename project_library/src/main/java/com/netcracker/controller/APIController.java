package com.netcracker.controller;

import com.netcracker.model.Author;
import com.netcracker.model.Book;
import com.netcracker.model.Filter;
import com.netcracker.service.AuthorService;
import com.netcracker.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class APIController {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookService bookService;

    @GetMapping("/api/authors")
    public List<Author> getAuthors() {
        List<Author> authors = authorService.getAuthors();
        return authors;
    }

    @PostMapping("/api/books")
    public List<Book> getBooks() {
        List<Book> books = bookService.getBooks();
        return books;
    }

    @PostMapping("/api/books/filter")
    public List<Book> filterForm (@RequestBody Filter filter) {
        List<Book> currentBooks = bookService.filterBooks(filter);
        return currentBooks;
    }
}
