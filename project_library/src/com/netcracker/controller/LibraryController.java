package com.netcracker.controller;

import com.netcracker.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LibraryController {

    @GetMapping("/library")
    public String libraryForm(Model model) {

        List<Book> books = new ArrayList<Book>();
        books.add(new Book(1, "Interesting Book", "Marvelo", "Very interesting book"));
        books.add(new Book(2, "Sad Book", "Printino", "Very sad book"));
        books.add(new Book(3,"Clever Book","Pichelatti","Very clever book"));
        model.addAttribute("books", books);
        return "library";
    }

}