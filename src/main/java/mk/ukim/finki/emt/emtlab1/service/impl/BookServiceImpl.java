package mk.ukim.finki.emt.emtlab1.service.impl;

import mk.ukim.finki.emt.emtlab1.model.Author;
import mk.ukim.finki.emt.emtlab1.model.Book;
import mk.ukim.finki.emt.emtlab1.model.dto.BookDto;
import mk.ukim.finki.emt.emtlab1.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt.emtlab1.model.exceptions.BookNotFoundException;
import mk.ukim.finki.emt.emtlab1.repository.AuthorRepository;
import mk.ukim.finki.emt.emtlab1.repository.BookRepository;
import mk.ukim.finki.emt.emtlab1.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public Optional<Book> addBook(BookDto book) throws AuthorNotFoundException {
        Author author = authorRepository.findById(book.getAuthorId()).orElseThrow(() -> new AuthorNotFoundException("Author with id: " + book.getAuthorId() + " was not found."));
        Book toBeAdded = new Book(book.getName(),book.getCategory(),author,book.getAvailableCopies());
        return Optional.of(bookRepository.save(toBeAdded));
    }

    @Override
    public Optional<Book> deleteBook(Long bookId) throws BookNotFoundException {
        Book toBeDeleted = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book with id: " + bookId + " was not found."));
        bookRepository.delete(toBeDeleted);
        return Optional.of(toBeDeleted);
    }

    @Override
    public Optional<Book> editBook(Long bookId, BookDto book) throws AuthorNotFoundException, BookNotFoundException {
        Author author = authorRepository.findById(book.getAuthorId()).orElseThrow(() -> new AuthorNotFoundException("Author with id: " + book.getAuthorId() + " was not found."));
        Book toBeEdited = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book with id: " + bookId + " was not found."));
        toBeEdited.setAvailableCopies(book.getAvailableCopies());
        toBeEdited.setAuthor(author);
        toBeEdited.setName(book.getName());
        toBeEdited.setCategory(book.getCategory());
        return Optional.of(bookRepository.save(toBeEdited));
    }

    @Override
    public void rentBook(Long bookId) throws BookNotFoundException {
        Book toBeRented = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book with id: " + bookId + " was not found."));
        toBeRented.setAvailableCopies(toBeRented.getAvailableCopies()-1);
        bookRepository.save(toBeRented);
    }

    @Override
    public List<Book> listAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findBook(Long id) {
        return bookRepository.findById(id);
    }
}
