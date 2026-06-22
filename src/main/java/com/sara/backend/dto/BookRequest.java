package com.sara.backend.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
public class BookRequest {
    @NotBlank

    private String title;

    @NotBlank

    private String author;

    @Positive

    private Double price;

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
