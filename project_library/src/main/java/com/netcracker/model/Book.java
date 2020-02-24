package com.netcracker.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String title;
    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name = "author")
    private Author author;
    private String description;
    private String rating;
    private String cost;
    @OneToMany(mappedBy= "book", fetch=FetchType.EAGER)
    private List<CartItem> cartItems;
    /*@OneToMany(mappedBy= "book", fetch=FetchType.EAGER)
    private List<Image> images;*/


    public Book() {
    }

    public Book(String id, String title, String description, String rating, String cost) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    /*public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) {
            return false;
        }
        Book book = (Book) o;
        return book.id.equals(id)
                && book.title.equals(title)
                && book.author.equals(author)
                && book.description.equals(description)
                && book.rating.equals(rating)
                && book.cost.equals(cost);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + rating.hashCode();
        result = 31 * result + cost.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author.toString() + '\'' +
                ", description='" + description + '\'' +
                ", rating='" + rating + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }
}
