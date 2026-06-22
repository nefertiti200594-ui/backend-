package com.sara.backend;
import com.sara.backend.dto.BookRequest;
import com.sara.backend.dto.BookResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {

        this.bookRepository = bookRepository;

    }

    public BookResponse addBook(BookRequest request) {

        Book book = new Book();

        book.setTitle(request.getTitle().trim());

        book.setAuthor(request.getAuthor().trim());

        book.setPrice(request.getPrice());

        Book saved = bookRepository.save(book);

        return mapToResponse(saved);
    }
    public List<BookResponse> getAllBooks() {

        return bookRepository.findAll()

                .stream()

                .map(this::mapToResponse)

                .toList();

}public String deleteBook(int id) {

        if (!bookRepository.existsById(id)) {

            throw new RuntimeException("Book tapılmadı");
        }

        bookRepository.deleteById(id);

        return "Book silindi";
    }
    public BookResponse updateBook(int id, BookRequest request) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setTitle(request.getTitle().trim());
        book.setAuthor(request.getAuthor().trim());
        book.setPrice(request.getPrice());

        Book updatedBook = bookRepository.save(book);

        return mapToResponse(updatedBook);
    }
    public BookResponse getBookById(int id) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        return mapToResponse(book);
    }

    private BookResponse mapToResponse(Book book) {

        return new BookResponse(

                book.getId(),

                book.getTitle(),

                book.getAuthor(),

                book.getPrice()

        );
    }
}
