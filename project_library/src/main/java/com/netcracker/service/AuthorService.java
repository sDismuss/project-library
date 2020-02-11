package com.netcracker.service;

import com.netcracker.model.Author;
import com.netcracker.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author findById(String id) {
        Optional<Author> optional = authorRepository.findById(id);
        return optional.orElse(null);
    }

    public List<Author> findByName(String name) {
        return authorRepository.findByName(name);
    }

    public void save(Author author) {
        authorRepository.save(author);
    }

    public List<Author> retrieveByName(String name) {
        return authorRepository.retrieveByName(name);
    }

    public List<Author> getAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors;
    }
}
