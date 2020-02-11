package com.netcracker.service;

import com.netcracker.model.Cart;
import com.netcracker.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    public Cart findById(String id) {
        Optional<Cart> optional = cartRepository.findById(id);
        return optional.orElse(null);
    }

    public List<Cart> findByBook(String book) {
        return cartRepository.findByBook(book);
    }

    public void save(Cart cart) {
        System.out.println(cart.toString() + "lalalalalalallaal");
        cartRepository.save(cart);
    }

    public List<Cart> retrieveByBook(String book) {
        return cartRepository.retrieveByBook(book);
    }

    public List<Cart> getCart() {
        List<Cart> cart = cartRepository.findAll();
        return cart;
    }
}