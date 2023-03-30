package mk.ukim.finki.emt.emtlab1.service;

import mk.ukim.finki.emt.emtlab1.model.Author;
import mk.ukim.finki.emt.emtlab1.model.Country;
import mk.ukim.finki.emt.emtlab1.model.dto.AuthorDto;
import mk.ukim.finki.emt.emtlab1.model.dto.CountryDto;
import mk.ukim.finki.emt.emtlab1.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt.emtlab1.model.exceptions.CountryNotFoundException;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> listAllAuthors();
    Optional<Author> findAuthorById(Long id);
    Optional<Author> addAuthor(AuthorDto author) throws CountryNotFoundException;
    Optional<Author> deleteAuthor(Long id) throws AuthorNotFoundException;
    Optional<Author> editAuthor(Long id, AuthorDto author) throws AuthorNotFoundException, CountryNotFoundException;
}
