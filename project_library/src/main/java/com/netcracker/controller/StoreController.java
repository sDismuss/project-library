package com.netcracker.controller;

import com.netcracker.model.Book;
import com.netcracker.model.Image;
import com.netcracker.model.User;
import com.netcracker.service.BookService;
import com.netcracker.service.ImageService;
import com.netcracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StoreController {
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    @GetMapping("/store")
    public String libraryForm(Model model) {
        List<Book> books = bookService.getBooks();
        model.addAttribute("books", books);
        Boolean logIn = userService.userLogIn();
        model.addAttribute("logIn", logIn);
        return "store";
    }
}