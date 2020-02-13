package com.netcracker;

import com.netcracker.model.CartItem;
import com.netcracker.service.BookService;
import com.netcracker.service.CartItemService;
import com.netcracker.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringBootStarter implements CommandLineRunner {
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private BookService bookService;
    @Autowired
    private CartService cartService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStarter.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        CartItem cartItem = new CartItem();
        cartItem.setId("100");
        cartItem.setBook(bookService.findById("1"));
        cartItem.setCart(cartService.findById("2"));
        System.out.println(cartItem.toString());
        cartItemService.save(cartItem);
        System.out.println("lalallalalalalalalalal");
    }
}