package com.cgi.countryInfoFetcher.service;

import com.cgi.countryInfoFetcher.model.CountryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//I am responsible to call the api service to fetch the countryResponse.
@Service
public class FetchCountriesService {

    @Autowired
    FetchCountriesApiService fetchCountriesApiService;

    public CountryResponse getCountryByCode(String code) {
        //Calling the api service which will call the restEnd point
        return fetchCountriesApiService.getCountryByCode(code);
    }
}
