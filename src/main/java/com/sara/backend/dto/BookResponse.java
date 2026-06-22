package com.sara.backend.dto;

public class BookResponse {
    private Integer id;

    private String title;

    private String author;

    private Double price;

    public BookResponse(Integer id,

                        String title,

                        String author,

                        Double price) {

        this.id = id;

        this.title = title;

        this.author = author;

        this.price = price;

    }

    public Integer getId() {

        return id;

    }

    public String getTitle() {

        return title;

    }

    public String getAuthor() {

        return author;

    }

    public Double getPrice() {

        return price;

    }
}
