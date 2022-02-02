package com.example.demo.services;

import com.example.demo.models.Country;
import com.example.demo.repositories.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    @Override
    public List<Country> getAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country add(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Country update(Country country) {
        Country existingCountry = countryRepository.findById(country.getId())
                .orElseThrow(() -> new EmptyResultDataAccessException(1));

        existingCountry.setName(country.getName());

        return countryRepository.save(existingCountry);
    }

    @Override
    public void delete(UUID id) {
        countryRepository.deleteById(id);
    }
}
