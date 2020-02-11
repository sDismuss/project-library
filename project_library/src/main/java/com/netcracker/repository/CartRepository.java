package main.java.com.netcracker.repository;

import main.java.com.netcracker.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, String> {
    List<Cart> findByBook(String book);

    @Query(value = "SELECT * FROM  public.cart c WHERE c.book = :book", nativeQuery = true)
    List<Cart> retrieveByBook(@Param("book") String book);

    @Override
    <S extends Cart> S save(S s);
}
