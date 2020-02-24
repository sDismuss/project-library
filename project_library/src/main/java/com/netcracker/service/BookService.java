package com.netcracker.service;

import com.netcracker.model.Book;
import com.netcracker.model.Image;
import com.netcracker.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorService authorService;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(String id) {
        Optional<Book> optional = bookRepository.findById(id);
        return optional.orElse(null);
    }

    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    public List<Book> retrieveByTitle(String title) {
        return bookRepository.retrieveByTitle(title);
    }

    public List<Book> getBooks() {
        List<Book> books = bookRepository.findAll();
        return books;
    }

}
