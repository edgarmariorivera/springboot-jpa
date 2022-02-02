package com.example.demo.services;

import com.example.demo.models.Country;

import java.util.List;
import java.util.UUID;

public interface CountryService {

    List<Country> getAll();
    Country add(Country country);
    Country update(Country country);
    void delete(UUID id);

}
