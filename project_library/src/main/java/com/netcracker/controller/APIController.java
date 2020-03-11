package com.netcracker.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.netcracker.model.Author;
import com.netcracker.model.Book;
import com.netcracker.model.Image;
import com.netcracker.service.AuthorService;
import com.netcracker.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @GetMapping("/api/books")
    public List<Book> getBooks() {
        List<Book> books = bookService.getBooks();
        return books;
    }


    /*@GetMapping("/api/authors/books")
    public List<Book> getBooks(@JsonFormat List<String> authors) {
        List<Book> currBooks = new ArrayList<>();
        for (String author: authors) {
            String id = authorService.findByName(author).get(0).getId();
            List<Book> books = bookService.findByAuthor(id);
            for (Book book: books){
                currBooks.add(book);
            }
        }
        return currBooks;
    }*/
}
