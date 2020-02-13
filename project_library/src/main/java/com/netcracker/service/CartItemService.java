package com.netcracker.service;

import com.netcracker.model.CartItem;
import com.netcracker.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    public List<CartItem> findAll() {
        return cartItemRepository.findAll();
    }

    public CartItem findById(String id) {
        Optional<CartItem> optional = cartItemRepository.findById(id);
        return optional.orElse(null);
    }

    public List<CartItem> findByBook(String book) {
        return cartItemRepository.findByBook(book);
    }

    public List<CartItem> findByCart(String cart) {
        return cartItemRepository.findByCart(cart);
    }

    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    public List<CartItem> retrieveByBook(String book) {
        return cartItemRepository.retrieveByBook(book);
    }

    public List<CartItem> retrieveByCart(String cart) { return cartItemRepository.retrieveByCart(cart); }
}