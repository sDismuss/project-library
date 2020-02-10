package com.netcracker.repository;

import com.netcracker.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, String> {
    List<Image> findByBook(String book);

    @Query(value = "SELECT * FROM  public.images i WHERE i.book = :book", nativeQuery = true)
    List<Image> retrieveByBook(@Param("book") String book);
}
