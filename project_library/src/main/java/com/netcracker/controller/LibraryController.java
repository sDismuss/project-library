package com.netcracker.controller;

import com.netcracker.model.Book;
import com.netcracker.model.MyUserPrincipal;
import com.netcracker.model.User;
import com.netcracker.service.BookService;
import com.netcracker.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LibraryController {
    @Autowired
    private BookService bookService;
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @GetMapping("/library")
    public String libraryForm(Model model) {
        String username;
        List<Book> books = bookService.getBooks();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        model.addAttribute("books", books);
        model.addAttribute("username", username);
        return "library";
    }


}