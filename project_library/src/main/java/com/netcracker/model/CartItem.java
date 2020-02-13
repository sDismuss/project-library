package com.netcracker.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cart_item")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "book")
    private Book book;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "cart")
    private Cart cart;

    public CartItem() {
    }

    public CartItem(Book book, Cart cart) {
        this.book = book;
        this.cart = cart;
    }

    public CartItem(String id, Book book, Cart cart) {
        this.id = id;
        this.book = book;
        this.cart = cart;
    }

    public CartItem(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(id, cartItem.id) &&
                Objects.equals(book, cartItem.book) &&
                Objects.equals(cart, cartItem.cart);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id.hashCode();
        result = 31 * result + book.hashCode();
        result = 31 * result + cart.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id='" + id + '\'' +
                ", book='" + book.toString() + '\'' +
                ", cart='" + cart.toString() + '\'' +
                '}';
    }
}
