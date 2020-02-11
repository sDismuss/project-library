package main.java.com.netcracker.repository;

import main.java.com.netcracker.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findByTitle(String title);

    @Query(value = "SELECT * FROM  public.books b WHERE b.title = :title", nativeQuery = true)
    List<Book> retrieveByTitle(@Param("title") String title);
}
