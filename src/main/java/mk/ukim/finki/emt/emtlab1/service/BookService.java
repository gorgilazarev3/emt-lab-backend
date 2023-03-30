package mk.ukim.finki.emt.emtlab1.service;

import mk.ukim.finki.emt.emtlab1.model.Book;
import mk.ukim.finki.emt.emtlab1.model.dto.BookDto;
import mk.ukim.finki.emt.emtlab1.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt.emtlab1.model.exceptions.BookNotFoundException;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> addBook(BookDto book) throws AuthorNotFoundException;
    Optional<Book> deleteBook(Long bookId) throws BookNotFoundException;
    Optional<Book> editBook(Long bookId, BookDto book) throws AuthorNotFoundException, BookNotFoundException;
    void rentBook(Long bookId) throws BookNotFoundException;
    List<Book> listAllBooks();
    Optional<Book> findBook(Long id);
}
