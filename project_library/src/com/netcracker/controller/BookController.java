package com.netcracker.controller;

import com.netcracker.model.Book;
import com.netcracker.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/library/book/{id}")
    public String bookForm(Model model, @PathVariable("id") String id){
       model.addAttribute("book", getID(id));
        return "book";
    }

    public Book getID(String id) {
        List<Book> books = bookService.getBooks();
        for (Book book: books) {
            if(book.getId().equals(id))
                return book;
        }
        return null;
    }
}