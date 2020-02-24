package com.netcracker.repository;

import com.netcracker.model.Book;
import com.netcracker.model.Cart;
import com.netcracker.model.CartItem;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, String> {
    List<CartItem> findByBook(Book book);

    List<CartItem> findByCart(Cart cart);

    @Query(value = "SELECT * FROM  public.cart_item c WHERE c.book = :book", nativeQuery = true)
    List<CartItem> retrieveByBook(@Param("book") Book book);

    @Query(value = "SELECT * FROM  public.cart_item c WHERE c.cart = :cart", nativeQuery = true)
    List<CartItem> retrieveByCart(@Param("cart") Cart cart);

    boolean existsByBook(Book book);

    @Override
    void delete(CartItem cartItem);
}