package com.sara.backend;
import com.sara.backend.dto.BookRequest;

import com.sara.backend.dto.BookResponse;

import jakarta.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/books")

public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {

        this.bookService = bookService;

    }

    @PostMapping

    public BookResponse addBook(@Valid @RequestBody BookRequest request) {

        return bookService.addBook(request);

    }

    @GetMapping
    public List<BookResponse> getAllBooks() {
        return bookService.getAllBooks();
    }
    @GetMapping("/{id}")
    public BookResponse getBookById(@PathVariable int id) {

        return bookService.getBookById(id);

    }
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {

        return bookService.deleteBook(id);
    }
    @PutMapping("/{id}")
    public BookResponse updateBook(
            @PathVariable int id,
            @Valid @RequestBody BookRequest request) {

        return bookService.updateBook(id, request);
    }
}
