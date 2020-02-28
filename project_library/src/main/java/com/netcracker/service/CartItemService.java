package com.netcracker.service;

import com.netcracker.model.Book;
import com.netcracker.model.Cart;
import com.netcracker.model.CartItem;
import com.netcracker.model.Image;
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

    public CartItem findById(Integer id) {
        Optional<CartItem> optional = cartItemRepository.findById(id);
        return optional.orElse(null);
    }

    public List<CartItem> findByBook(Book book) {
        return cartItemRepository.findByBook(book);
    }

    public List<CartItem> findByCart(Cart cart) {
        return cartItemRepository.findByCart(cart);
    }

    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    public List<CartItem> retrieveByBook(Book book) {
        return cartItemRepository.retrieveByBook(book);
    }

    public List<CartItem> retrieveByCart(Cart cart) {
        return cartItemRepository.retrieveByCart(cart);
    }

    public boolean existsByBook(Book book) {return cartItemRepository.existsByBook(book);}

    public void delete(CartItem cartItem) { cartItemRepository.delete(cartItem);};

    public void addBook(CartItem cartItem) {
        cartItem.setQuantity(Integer.toString(Integer.parseInt(cartItem.getQuantity()) + 1));
        this.save(cartItem);
    }
}