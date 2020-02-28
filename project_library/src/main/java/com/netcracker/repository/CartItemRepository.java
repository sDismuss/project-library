package com.netcracker.repository;

import com.netcracker.model.Book;
import com.netcracker.model.Cart;
import com.netcracker.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findByBook(Book book);

    List<CartItem> findByCart(Cart cart);

    Optional<CartItem> findById(Integer id);

    /*List<CartItem> findByCart_Id(Integer id);*/

    @Query(value = "SELECT * FROM  public.cart_item c WHERE c.book = :book", nativeQuery = true)
    List<CartItem> retrieveByBook(@Param("book") Book book);

    @Query(value = "SELECT * FROM  public.cart_item c WHERE c.cart = :cart", nativeQuery = true)
    List<CartItem> retrieveByCart(@Param("cart") Cart cart);

    boolean existsByBook(Book book);

    @Override
    void delete(CartItem cartItem);
}