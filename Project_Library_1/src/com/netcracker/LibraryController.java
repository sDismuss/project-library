package com.netcracker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LibraryController {

    @GetMapping("/library")
    public String libraryForm(Model model) {
        model.addAttribute("book1", new Book(1, "Interesting Book", "Marvelo", "Very interesting book"));
        model.addAttribute("book2", new Book(2, "Sad Book", "Printino", "Very sad book"));
        model.addAttribute("book3", new Book(3,"Clever Book","Pichelatti","Very clever book"));
        return "library";
    }
}