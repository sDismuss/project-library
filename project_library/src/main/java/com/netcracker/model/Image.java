package com.netcracker.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String link;
    private String book;

    public Image() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return Objects.equals(id, image.id) &&
                Objects.equals(link, image.link) &&
                Objects.equals(image, image.book);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + id.hashCode();
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
