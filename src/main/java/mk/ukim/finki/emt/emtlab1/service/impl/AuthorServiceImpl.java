package mk.ukim.finki.emt.emtlab1.service.impl;

import mk.ukim.finki.emt.emtlab1.model.Author;
import mk.ukim.finki.emt.emtlab1.model.Country;
import mk.ukim.finki.emt.emtlab1.model.dto.AuthorDto;
import mk.ukim.finki.emt.emtlab1.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt.emtlab1.model.exceptions.CountryNotFoundException;
import mk.ukim.finki.emt.emtlab1.repository.AuthorRepository;
import mk.ukim.finki.emt.emtlab1.repository.CountryRepository;
import mk.ukim.finki.emt.emtlab1.service.AuthorService;
import mk.ukim.finki.emt.emtlab1.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> listAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Optional<Author> addAuthor(AuthorDto author) throws CountryNotFoundException {
        Country country = countryRepository.findById(author.getCountryId()).orElseThrow(() -> new CountryNotFoundException("Country with id: " + author.getCountryId() + " was not found."));
        Author toBeAdded = new Author(author.getName(),author.getSurname(),country);
        return Optional.of(authorRepository.save(toBeAdded));
    }

    @Override
    public Optional<Author> deleteAuthor(Long id) throws AuthorNotFoundException {
        Author toBeDeleted = authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException("Author with id: " + id + " was not found."));
        authorRepository.delete(toBeDeleted);
        return Optional.of(toBeDeleted);
    }

    @Override
    public Optional<Author> editAuthor(Long id, AuthorDto author) throws AuthorNotFoundException, CountryNotFoundException {
        Country country = countryRepository.findById(author.getCountryId()).orElseThrow(() -> new CountryNotFoundException("Country with id: " + author.getCountryId() + " was not found."));
        Author toBeEdited = authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException("Author with id: " + id + " was not found."));
        toBeEdited.setName(author.getName());
        toBeEdited.setSurname(author.getSurname());
        toBeEdited.setCountry(country);
        return Optional.of(authorRepository.save(toBeEdited));
    }
}
