package com.netcracker;

import com.netcracker.model.Cart;
import com.netcracker.service.CartService;
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
    private CartService cartService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStarter.class, args);
    }

    @Override
    public void run(String... args) {


        /*log.info("find all");
        cartService.findAll().forEach(System.out::println);

        log.info("find by id");
        System.out.println(cartService.findById("1"));

        log.info("find by book");
        cartService.findByBook("1").forEach(System.out::println);

        log.info("save new book");
        cartService.save(new Cart("2","3"));

        log.info("retreive by book");
        cartService.retrieveByBook("1").forEach(System.out::println);
*/
    }


}