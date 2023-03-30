package mk.ukim.finki.emt.emtlab1.controller;

import mk.ukim.finki.emt.emtlab1.model.Book;
import mk.ukim.finki.emt.emtlab1.model.dto.BookDto;
import mk.ukim.finki.emt.emtlab1.model.enumerations.BookType;
import mk.ukim.finki.emt.emtlab1.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt.emtlab1.model.exceptions.BookNotFoundException;
import mk.ukim.finki.emt.emtlab1.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    List<Book> getAllBooks() {
        return bookService.listAllBooks();
    }

    @GetMapping("/{id}")
    ResponseEntity<Book> getBook(@PathVariable Long id) {
        Optional<Book> book = bookService.findBook(id);
        if(book.isPresent()) {
            return ResponseEntity.ok(book.get());
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    ResponseEntity<Book> addBook(@RequestBody BookDto bookDto) throws AuthorNotFoundException {
        Optional<Book> addedBook = bookService.addBook(bookDto);
        if(addedBook.isPresent()) {
            return ResponseEntity.ok(addedBook.get());
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<Book> editBook(@PathVariable Long id, @RequestBody BookDto bookDto) throws AuthorNotFoundException, BookNotFoundException {
        Optional<Book> editedBook = bookService.editBook(id, bookDto);
        if(editedBook.isPresent()) {
            return ResponseEntity.ok(editedBook.get());
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Book> deleteBook(@PathVariable Long id) throws BookNotFoundException {
        Optional<Book> deletedBook = bookService.deleteBook(id);
        if(deletedBook.isPresent()) {
            return ResponseEntity.ok(deletedBook.get());
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/rent/{id}")
    ResponseEntity<Book> rentBook(@PathVariable Long id) throws BookNotFoundException {
        Optional<Book> toRent = bookService.findBook(id);
        if(toRent.isPresent()) {
            bookService.rentBook(toRent.get().getId());
            return ResponseEntity.ok(toRent.get());
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

}
