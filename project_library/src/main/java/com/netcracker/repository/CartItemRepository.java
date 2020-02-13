package com.netcracker.repository;

import com.netcracker.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, String> {
    List<CartItem> findByBook(String book);

    List<CartItem> findByCart(String cart);

    @Query(value = "SELECT * FROM  public.cart_item c WHERE c.book = :book", nativeQuery = true)
    List<CartItem> retrieveByBook(@Param("book") String book);

    @Query(value = "SELECT * FROM  public.cart_item c WHERE c.cart = :cart", nativeQuery = true)
    List<CartItem> retrieveByCart(@Param("cart") String cart);

}