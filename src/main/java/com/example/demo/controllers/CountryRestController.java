package com.example.demo.controllers;

import com.example.demo.models.Country;
import com.example.demo.services.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.UUID.fromString;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class CountryRestController {

    private final CountryService countryService;

    @GetMapping(path = "/countries")
    @ResponseStatus(OK)
    public List<Country> getAllCountries() {
        return countryService.getAll();
    }

    @PostMapping(path = "/countries")
    @ResponseStatus(CREATED)
    public Country addCountry(@RequestBody Country country) {
        return countryService.add(country);
    }

    @PutMapping(path = "/countries/{id}")
    @ResponseStatus(OK)
    public Country updateCountry(@PathVariable String id, @RequestBody Country country) {
        country.setId(fromString(id));
        return countryService.update(country);
    }

    @DeleteMapping(path = "/countries/{id}")
    @ResponseStatus(OK)
    public void deleteCountry(@PathVariable String id) {
        countryService.delete(fromString(id));
    }

}
