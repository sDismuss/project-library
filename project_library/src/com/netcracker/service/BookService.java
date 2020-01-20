package com.netcracker.service;

import com.netcracker.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    public List<Book> getBooks() {
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("1", "Interesting Book", "Marvelo", "Very interesting book","5.0","1000"));
        books.add(new Book("2", "Sad Book", "Printino", "Very sad book", "3.5", "500"));
        books.add(new Book("3","Clever Book","Pichelatti","Very clever book", "4.6", "700"));

        return books;
    }
}
