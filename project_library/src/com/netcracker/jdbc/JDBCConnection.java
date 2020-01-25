package com.netcracker.jdbc;

import com.netcracker.model.Author;
import com.netcracker.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCConnection {
    private Connection connection;

    public JDBCConnection() {
        getConnection();
    }

    private void getConnection() {

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library", "postgres", "postgres");
            connection.setAutoCommit(false);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Book> executeSimpleQuery() {

        List<Book> books = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            String selectSql = "SELECT * FROM public.books";

            ResultSet resultSet = stmt.executeQuery(selectSql);

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getString("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setDescription(resultSet.getString("description"));
                book.setRating(resultSet.getString("rating"));
                book.setCost(resultSet.getString("book_cost"));
                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;

    }

    public static void main(String[] args) {
        JDBCConnection jdbcConnection = new JDBCConnection();
        List<Book> result = jdbcConnection.executeSimpleQuery();
        result.forEach(System.out::println);
    }

}
