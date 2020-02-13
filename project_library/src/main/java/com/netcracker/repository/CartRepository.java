package com.netcracker.repository;

import com.netcracker.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, String> {
    List<Cart> findByUserID(String userID);

    @Query(value = "SELECT * FROM  public.cart c WHERE c.userID = :userID", nativeQuery = true)
    List<Cart> retrieveByUser(@Param("userID") String userID);
}
