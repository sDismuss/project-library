package com.netcracker;

import com.netcracker.model.Book;
import com.netcracker.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class SpringBootStarter implements CommandLineRunner{

    private static final Logger log = LoggerFactory.getLogger(SpringBootStarter.class);

    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStarter.class, args);
    }

    @Override
    public void run(String... args) {

        log.info("find all");
        bookService.findAll().forEach(System.out::println);

        log.info("find by id");
        System.out.println(bookService.findById("2"));

        log.info("find by title");
        bookService.findByTitle("Mobil Book").forEach(System.out::println);

        log.info("save new book");

        //bookService.save(new Book("4", "Mobil Book", "Gleb", "Just mobil book", "2.3", "10000000"));

        log.info("retreive by title");
        bookService.retrieveByTitle("Clever Book").forEach(System.out::println);

    }

}