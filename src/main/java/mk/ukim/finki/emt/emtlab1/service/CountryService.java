package mk.ukim.finki.emt.emtlab1.service;

import mk.ukim.finki.emt.emtlab1.model.Country;
import mk.ukim.finki.emt.emtlab1.model.dto.CountryDto;
import mk.ukim.finki.emt.emtlab1.model.exceptions.CountryNotFoundException;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> listAllCountries();
    Optional<Country> findCountryById(Long id);
    Optional<Country> findCountryByName(String name);
    Optional<Country> addCountry(CountryDto country);
    Optional<Country> deleteCountry(Long id) throws CountryNotFoundException;
    Optional<Country> editCountry(Long id, CountryDto country) throws CountryNotFoundException;
}
