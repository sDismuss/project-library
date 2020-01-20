package com.netcracker.controller;

import com.netcracker.model.Book;
import com.netcracker.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/library/book/{id}")
    public String bookForm(Model model, @PathVariable("id") String id){
        List<Book> books = bookService.getBooks();
        model.addAttribute("book", books.get(Integer.parseInt (id) - 1));
        return "book";
    }
}