package com.netcracker.controller;

import com.netcracker.model.Book;
import com.netcracker.model.CartItem;
import com.netcracker.service.BookService;
import com.netcracker.service.CartItemService;
import com.netcracker.service.CartService;
import com.netcracker.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/library/cart")
    public String cartForm(Model model) {
        List<CartItem> cartItems = cartService.findById("1").getBooks();
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("cart", cartService);
        return "cart";
    }

    @RequestMapping("/library/cart/add/{id}")
    public String addToCart(@PathVariable("id") String bookID, Model model) {
        Book currBook = bookService.findById(bookID);
        if (cartItemService.existsByBook(currBook)) {
            List<CartItem> listCartItem = cartItemService.findByBook(currBook);
            for (CartItem cartItem : listCartItem) {
                cartItem.setQuantity(Integer.toString(Integer.parseInt(cartItem.getQuantity()) + 1));
                cartItemService.save(cartItem);
            }
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setBook(currBook);
            cartItem.setCart(cartService.findById("1"));
            cartItem.setQuantity("1");
            cartItemService.save(cartItem);
        }
        return "transitionToCart";
    }

    @RequestMapping("/library/cart/delete/{id}")
    public String deleteToCart(@PathVariable("id") String bookID) {
        Book currBook = bookService.findById(bookID);
        if (cartItemService.existsByBook(currBook)) {
            List<CartItem> listCartItem = cartItemService.findByBook(currBook);
            for (CartItem cartItem : listCartItem) {
                cartItemService.delete(cartItem);
            }
        }
        return "transitionToCart";
    }


}

