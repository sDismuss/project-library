package com.netcracker.repository;

import com.netcracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> findByLogin(String login);

    @Query(value = "SELECT * FROM  public.users i WHERE i.login = :login", nativeQuery = true)
    List<User> retrieveByLogin(@Param("login") String login);
}


