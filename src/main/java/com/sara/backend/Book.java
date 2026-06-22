package com.sara.backend;

import jakarta.persistence.*;

@Entity

@Table(name = "books")
public class Book {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    private String title;

    private String author;

    private Double price;

    public Book() {

    }

    public Integer getId() {

        return id;

    }

    public void setId(Integer id) {

        this.id = id;

    }

    public String getTitle() {

        return title;

    }

    public void setTitle(String title) {

        this.title = title;

    }

    public String getAuthor() {

        return author;

    }

    public void setAuthor(String author) {

        this.author = author;

    }

    public Double getPrice() {

        return price;

    }

    public void setPrice(Double price) {

        this.price = price;

    }

}

