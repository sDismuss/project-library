package com.netcracker.controller;

import com.netcracker.model.Author;
import com.netcracker.model.Book;
import com.netcracker.service.AuthorService;
import com.netcracker.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class APIController {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookService bookService;

    @GetMapping("/api/authors")
    public List<Author> getAuthors() {
        List<Author> authors = authorService.getAuthors();
        return authors;
    }

    @PostMapping("/api/books")
    public List<Book> getBooks() {
        List<Book> books = bookService.getBooks();
        return books;
    }

    @PostMapping("/api/books/filter")
    public List<Book> filterForm(@RequestBody String filter) {
        StringBuffer filterText = new StringBuffer(filter);
        List<String> authorNames = getAuthorNames(filterText);
        if(authorNames.isEmpty()) {
            List<Author> authors = authorService.getAuthors();
            for (Author author: authors) {
                authorNames.add(author.getName());
            }
        }
        List<Integer> costs = getCosts(filterText);
        List<Book> allBooks = bookService.getBooks();
        List<Book> currentBooks = new ArrayList<>();
        for (Book book: allBooks) {
            Boolean checked = false;
            for (String author: authorNames) {
                if (book.getAuthor().getName().equals(author)) {
                    checked = true;
                }
            }
            if(checked &&
                    Integer.parseInt(book.getCost()) >= costs.get(0) &&
                    Integer.parseInt(book.getCost()) <= costs.get(1)) {
                currentBooks.add(book);
            }
        }
        return currentBooks;
    }

    private List<String> getAuthorNames (StringBuffer stringBuffer) {
        List<String> authorNames = new ArrayList<>();
        stringBuffer.delete(0, stringBuffer.indexOf("Authors"));
        stringBuffer.delete(0, 10);
        while (stringBuffer.charAt(0) != '}') {
            int firstAuthorIndex = 0,
                    lastAuthorIndex = 0;
            firstAuthorIndex = stringBuffer.indexOf(":") + 2;
            stringBuffer.delete(0, firstAuthorIndex);
            lastAuthorIndex = stringBuffer.indexOf("\"");
            String nextAuthor = stringBuffer.substring(0, lastAuthorIndex);
            authorNames.add(nextAuthor);
            stringBuffer.delete(0, lastAuthorIndex + 1);
        }
        return authorNames;
    }

    private List<Integer> getCosts (StringBuffer stringBuffer) {
        List<Integer> costs = new ArrayList<>();
        stringBuffer.delete(0, stringBuffer.indexOf("Cost"));
        stringBuffer.delete(0, 7);
        while (stringBuffer.charAt(0) != '}') {
            int firstCostIndex = 0,
                    lastCostIndex = 0;
            firstCostIndex = stringBuffer.indexOf(":") + 2;
            stringBuffer.delete(0, firstCostIndex);
            lastCostIndex = stringBuffer.indexOf("\"");
            Integer nextCost = Integer.parseInt(stringBuffer.substring(0, lastCostIndex));
            costs.add(nextCost);
            stringBuffer.delete(0, lastCostIndex + 1);
        }
        return costs;
    }
}
