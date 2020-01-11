package com.netcracker.controller;

import com.netcracker.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LibraryController {

    Book[] books = new Book[3];
    List<String> authors;
    @GetMapping("/library")
    public String libraryForm(Model model) {
        books[0] = new Book(1, "Interesting Book", "Marvelo", "Very interesting book");
        books[1] = new Book(2, "title", "Printino", "Very sad book");
        books[2] = new Book(3,"Clever Book","Pichelatti","Very clever book");
        model.addAttribute("books", books);
        return "library";
    }

}