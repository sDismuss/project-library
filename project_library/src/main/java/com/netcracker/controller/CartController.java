package com.netcracker.controller;

import com.netcracker.model.Book;
import com.netcracker.model.Cart;
import com.netcracker.model.CartItem;
import com.netcracker.service.BookService;
import com.netcracker.service.CartItemService;
import com.netcracker.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private BookService bookService;
    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/library/cart")
    public String cartForm(Model model) {
        Cart cart = cartService.findById("1");
        model.addAttribute("cart", cart);
        return "cart";
    }

    @RequestMapping("/library/cart/add/{id}")
    public void addToCart(@PathVariable("id") String bookID) {
        CartItem cartItem = new CartItem();
        Book nBook = bookService.findById(bookID);
        cartItem.setBook(nBook);
        cartItem.setCart(cartService.findById("1"));
        cartItemService.save(cartItem);
    }
}
