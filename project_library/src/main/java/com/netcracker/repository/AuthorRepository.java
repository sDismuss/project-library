package com.netcracker.repository;

import com.netcracker.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, String> {
    List<Author> findByName(String name);

    @Query(value = "SELECT * FROM  public.authors a WHERE a.name = :name", nativeQuery = true)
    List<Author> retrieveByName(@Param("name") String name);
}
