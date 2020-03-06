package com.netcracker.controller;

import com.netcracker.model.Book;
import com.netcracker.model.Image;
import com.netcracker.service.BookService;
import com.netcracker.service.ImageService;
import com.netcracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private UserService userService;

    @GetMapping("/store/book/{id}")
    public String bookForm(Model model, @PathVariable("id") String id) {
        Boolean logIn = userService.userLogIn();
        model.addAttribute("logIn", logIn);
        model.addAttribute("book", getID(id));
        model.addAttribute("images", getImages(id));
        return "book";
    }

    public Book getID(String id) {
        List<Book> books = bookService.getBooks();
        for (Book book : books) {
            if (book.getId().equals(id))
                return book;
        }
        return null;
    }

    public List<Image> getImages(String id) {
        List<Image> images = imageService.getImages();
        List<Image> currImages = new ArrayList<>();
        for (Image image : images) {
            if (image.getBook().equals(id))
                currImages.add(image);
        }
        return currImages;
    }
}