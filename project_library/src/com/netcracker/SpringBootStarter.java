package com.netcracker;

import com.netcracker.model.Author;
import com.netcracker.service.AuthorService;
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
    private AuthorService authorService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStarter.class, args);
    }

    @Override
    public void run(String... args) {


        log.info("find by id");
        System.out.println(authorService.findById("2"));


        log.info("find all");
        authorService.findAll().forEach(System.out::println);



        log.info("find by title");
        authorService.findByName("Mobil Book").forEach(System.out::println);

        log.info("save new book");

        authorService.save(new Author("4","Gleb"));

        log.info("retreive by title");
        authorService.retrieveByName("Clever Book").forEach(System.out::println);

    }

}