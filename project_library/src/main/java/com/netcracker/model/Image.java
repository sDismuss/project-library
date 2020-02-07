package com.netcracker.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String link;
    private String book;

    public Image() {
    }

    public Image(String link, String book) {
        this.link = link;
        this.book = book;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(link, image.link) &&
                Objects.equals(book, image.book);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + link.hashCode();
        result = 31 * result + book.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Image{" +
                "link='" + link + '\'' +
                ", book='" + book + '\'' +
                '}';
    }
}
