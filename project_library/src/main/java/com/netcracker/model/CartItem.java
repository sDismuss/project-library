package com.netcracker.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cart_item")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "book")
    private Book book;
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cart")
    private Cart cart;
    private String quantity;

    public CartItem() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCost() { return Integer.toString(Integer.parseInt(book.getCost()) * Integer.parseInt(quantity));}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return (id == cartItem.id) &&
                Objects.equals(book, cartItem.book) &&
                Objects.equals(cart, cartItem.cart) &&
                Objects.equals(quantity, cartItem.quantity);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id;
        result = 31 * result + book.hashCode();
        result = 31 * result + cart.hashCode();
        result = 31 * result + quantity.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id='" + id + '\'' +
                ", book='" + book.toString() + '\'' +
                ", cart='" + cart.toString() + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}
