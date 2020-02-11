package main.java.com.netcracker.controller;

import main.java.com.netcracker.model.Author;
import main.java.com.netcracker.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class APIController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/api/authors")
    public List<Author> getAuthors() {
        List<Author> authors = authorService.getAuthors();
        return authors;
    }
}
