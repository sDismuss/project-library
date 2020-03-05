package com.netcracker.controller;

import com.netcracker.model.Book;
import com.netcracker.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LibraryController {
    @Autowired
    private BookService bookService;

    @GetMapping("/store")
    public String libraryForm(Model model) {
        List<Book> books = bookService.getBooks();
        model.addAttribute("books", books);
        return "store";
    }
}