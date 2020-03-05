package com.netcracker.service;

import com.netcracker.model.Cart;
import com.netcracker.model.CartItem;
import com.netcracker.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartService cartService;
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private UserService userService;

    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    public Cart findById(String id) {
        Optional<Cart> optional = cartRepository.findById(id);
        return optional.orElse(null);
    }

    public Cart findByUserID(String userID) {
        return cartRepository.findByUserID(userID);
    }

    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    public List<Cart> retrieveByUser(String userID) {
        return cartRepository.retrieveByUser(userID);
    }

    public String totalCost(String id) {
        List<CartItem> cartItems = findById(id).getBooks();
        Integer total = 0;
        for (CartItem cartItem : cartItems) {
            total = total + Integer.parseInt(cartItem.getCost());
        }
        return Integer.toString(total);
    }

    public Cart getCurrentCart() {
        String currUsername = myUserDetailsService.getCurrentUsername();
        Integer currUserId = userService.findByUsername(currUsername).getId();
        Cart cart = cartService.findByUserID(Integer.toString(currUserId));
        return cart;
    }
}