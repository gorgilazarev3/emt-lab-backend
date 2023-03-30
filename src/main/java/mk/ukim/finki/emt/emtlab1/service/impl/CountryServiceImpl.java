package mk.ukim.finki.emt.emtlab1.service.impl;

import mk.ukim.finki.emt.emtlab1.model.Country;
import mk.ukim.finki.emt.emtlab1.model.dto.CountryDto;
import mk.ukim.finki.emt.emtlab1.model.exceptions.CountryNotFoundException;
import mk.ukim.finki.emt.emtlab1.repository.CountryRepository;
import mk.ukim.finki.emt.emtlab1.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> listAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findCountryById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Optional<Country> findCountryByName(String name) {
        return countryRepository.findByName(name);
    }

    @Override
    public Optional<Country> addCountry(CountryDto country) {
        Country toBeAdded = new Country(country.getName(), country.getContinent());
        return Optional.of(countryRepository.save(toBeAdded));
    }

    @Override
    public Optional<Country> deleteCountry(Long id) throws CountryNotFoundException {
        Country toBeDeleted = countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException("Country with id: " + id + " was not found."));
        countryRepository.delete(toBeDeleted);
        return Optional.of(toBeDeleted);
    }

    @Override
    public Optional<Country> editCountry(Long id, CountryDto country) throws CountryNotFoundException {
        Country toBeEdited = countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException("Country with id: " + id + " was not found."));
        toBeEdited.setName(country.getName());
        toBeEdited.setContinent(country.getContinent());
        return Optional.of(countryRepository.save(toBeEdited));
    }
}
