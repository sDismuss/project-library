package com.netcracker.controller;

import com.netcracker.model.Book;
import com.netcracker.model.Cart;
import com.netcracker.model.CartItem;
import com.netcracker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private BookService bookService;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private UserService userService;

    @GetMapping("/cart")
    public String cartForm(Model model) {
        Cart cart = cartService.getCurrentCart();
        List<CartItem> cartItems = cart.getBooks();
        model.addAttribute("cartItems", cartItems);
        String total = cartService.totalCost(cart.getId());
        model.addAttribute("total", total);
        Boolean logIn = userService.userLogIn();
        model.addAttribute("logIn", logIn);
        return "cart";
    }

    @GetMapping("/cart/delete")
    public String deleteAll() {
        cartService.deleteAll();
        return "transitionToCart";
    }

    @RequestMapping("/cart/add/{id}")
    public String addToCart(@PathVariable("id") String bookID, Model model) {
        Cart cart = cartService.getCurrentCart();
        List<CartItem> cartItems = cart.getBooks();
        Book currBook = bookService.findById(bookID);
        if (cartItemService.existsByBook(cartItems, currBook)) {
            for (CartItem cartItem : cartItems) {
                if (cartItem.getBook().equals(currBook)) {
                    cartItem.setQuantity(Integer.toString(Integer.parseInt(cartItem.getQuantity()) + 1));
                    cartItemService.save(cartItem);
                }
            }
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setBook(currBook);
            cartItem.setCart(cart);
            cartItem.setQuantity("1");
            cartItemService.save(cartItem);
        }
        return "transitionToCart";
    }

    @RequestMapping("/cart/delete/{id}")
    public String deleteToCart(@PathVariable("id") String bookID) {
        Cart cart = cartService.getCurrentCart();
        List<CartItem> cartItems = cart.getBooks();
        Book currBook = bookService.findById(bookID);
        if (cartItemService.existsByBook(cartItems, currBook)) {
            for (CartItem cartItem : cartItems) {
                if (cartItem.getBook().equals(currBook)) {
                    cartItemService.delete(cartItem);
                }
            }
        }
        return "transitionToCart";
    }
}

