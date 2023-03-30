package mk.ukim.finki.emt.emtlab1.bootstrap;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import mk.ukim.finki.emt.emtlab1.model.Author;
import mk.ukim.finki.emt.emtlab1.model.Book;
import mk.ukim.finki.emt.emtlab1.model.Country;
import mk.ukim.finki.emt.emtlab1.model.enumerations.BookType;
import mk.ukim.finki.emt.emtlab1.repository.AuthorRepository;
import mk.ukim.finki.emt.emtlab1.repository.BookRepository;
import mk.ukim.finki.emt.emtlab1.repository.CountryRepository;
import org.springframework.stereotype.Component;

@Component
@Getter
public class DataInitializer {

    private final CountryRepository countryRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public DataInitializer(CountryRepository countryRepository, AuthorRepository authorRepository, BookRepository bookRepository) {
        this.countryRepository = countryRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    void initializeData() {
        //Countries init
        Country country1 = new Country("Macedonia","Europe");
        Country country2 = new Country("USA","North America");
        Country country3 = new Country("Australia","Australia");
        countryRepository.save(country1);
        countryRepository.save(country2);
        countryRepository.save(country3);

        //Authors init
        Author author1 = new Author("Kosta","Racin", country1);
        Author author2 = new Author("Ernest","Hemingway", country2);
        Author author3 = new Author("Jane","Harper",country3);
        authorRepository.save(author1);
        authorRepository.save(author2);
        authorRepository.save(author3);

        //Books init
        Book book1 = new Book("White Dawns", BookType.CLASSICS, author1, 50);
        Book book2 = new Book("The old man and the sea",BookType.NOVEL, author2,38);
        Book book3 = new Book("Exiles",BookType.THRILLER,author3,12);
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
    }
}
