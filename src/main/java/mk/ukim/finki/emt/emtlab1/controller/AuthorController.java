package mk.ukim.finki.emt.emtlab1.controller;

import mk.ukim.finki.emt.emtlab1.model.Author;
import mk.ukim.finki.emt.emtlab1.model.Book;
import mk.ukim.finki.emt.emtlab1.model.Country;
import mk.ukim.finki.emt.emtlab1.model.dto.AuthorDto;
import mk.ukim.finki.emt.emtlab1.model.dto.CountryDto;
import mk.ukim.finki.emt.emtlab1.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt.emtlab1.model.exceptions.CountryNotFoundException;
import mk.ukim.finki.emt.emtlab1.service.AuthorService;
import mk.ukim.finki.emt.emtlab1.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    List<Author> getAllAuthors() {
        return authorService.listAllAuthors();
    }

    @GetMapping("/{id}")
    ResponseEntity<Author> getAuthor(@PathVariable Long id) {
        Optional<Author> author = authorService.findAuthorById(id);
        if(author.isPresent()) {
            return ResponseEntity.ok(author.get());
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    ResponseEntity<Author> addAuthor(@RequestBody AuthorDto authorDto) throws CountryNotFoundException {
        Optional<Author> addedAuthor = authorService.addAuthor(authorDto);
        if(addedAuthor.isPresent()) {
            return ResponseEntity.ok(addedAuthor.get());
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<Author> editAuthor(@PathVariable Long id, @RequestBody AuthorDto authorDto) throws AuthorNotFoundException, CountryNotFoundException {
        Optional<Author> editedAuthor = authorService.editAuthor(id, authorDto);
        if(editedAuthor.isPresent()) {
            return ResponseEntity.ok(editedAuthor.get());
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Author> deleteAuthor(@PathVariable Long id) throws AuthorNotFoundException {
        Optional<Author> deletedAuthor = authorService.deleteAuthor(id);
        if(deletedAuthor.isPresent()) {
            return ResponseEntity.ok(deletedAuthor.get());
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }
}
