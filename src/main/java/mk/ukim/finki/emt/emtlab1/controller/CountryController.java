package mk.ukim.finki.emt.emtlab1.controller;

import mk.ukim.finki.emt.emtlab1.model.Author;
import mk.ukim.finki.emt.emtlab1.model.Book;
import mk.ukim.finki.emt.emtlab1.model.Country;
import mk.ukim.finki.emt.emtlab1.model.dto.BookDto;
import mk.ukim.finki.emt.emtlab1.model.dto.CountryDto;
import mk.ukim.finki.emt.emtlab1.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt.emtlab1.model.exceptions.BookNotFoundException;
import mk.ukim.finki.emt.emtlab1.model.exceptions.CountryNotFoundException;
import mk.ukim.finki.emt.emtlab1.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/country")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    List<Country> getAllCountries() {
        return countryService.listAllCountries();
    }

    @GetMapping("/{id}")
    ResponseEntity<Country> getCountry(@PathVariable Long id) {
        Optional<Country> country = countryService.findCountryById(id);
        if(country.isPresent()) {
            return ResponseEntity.ok(country.get());
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    ResponseEntity<Country> addCountry(@RequestBody CountryDto countryDto) {
        Optional<Country> addedCountry = countryService.addCountry(countryDto);
        if(addedCountry.isPresent()) {
            return ResponseEntity.ok(addedCountry.get());
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<Country> editCountry(@PathVariable Long id, @RequestBody CountryDto countryDto) throws CountryNotFoundException {
        Optional<Country> editedCountry = countryService.editCountry(id, countryDto);
        if(editedCountry.isPresent()) {
            return ResponseEntity.ok(editedCountry.get());
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Country> deleteCountry(@PathVariable Long id) throws CountryNotFoundException {
        Optional<Country> deletedCountry = countryService.deleteCountry(id);
        if(deletedCountry.isPresent()) {
            return ResponseEntity.ok(deletedCountry.get());
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }
}
