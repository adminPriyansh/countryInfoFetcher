package com.cgi.countryInfoFetcher.service;

import com.cgi.countryInfoFetcher.model.Country;
import com.cgi.countryInfoFetcher.model.CountryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
public class FetchCountriesService {

    @Autowired
    FetchCountriesApiService fetchCountriesApiService;

    public CountryResponse getCountryByCode(String code) {
        return fetchCountriesApiService.getCountryByCode(code);
    }
}
