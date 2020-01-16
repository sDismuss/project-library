package com.netcracker.controller;

import com.netcracker.model.Author;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class APIController {

    @GetMapping("/api/authors")
    public List<Author> getAuthors(){
        List<Author> authors = new ArrayList<Author>();
        authors.add(new Author("1","Пушкин"));
        authors.add(new Author("2","Лермонтов"));
        return authors;
    }
}
