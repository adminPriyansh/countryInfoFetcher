package com.cgi.countryInfoFetcher.controller;

import com.cgi.countryInfoFetcher.exception.InvalidCodeFormatException;
import com.cgi.countryInfoFetcher.model.CountryResponse;
import com.cgi.countryInfoFetcher.service.FetchCountriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FetchCountriesController {

    @Autowired
    FetchCountriesService fetchCountriesService;


    @GetMapping("/countries/{code}")
    public ResponseEntity<CountryResponse> getCountryByCode(@PathVariable String code) {

        //Validate if the code is exactly two uppercase letters
        if (!code.matches("^[A-Z]{2}$")) {
            throw new InvalidCodeFormatException("Invalid country code format");
        }
        return ResponseEntity.ok(fetchCountriesService.getCountryByCode(code));
    }
}
