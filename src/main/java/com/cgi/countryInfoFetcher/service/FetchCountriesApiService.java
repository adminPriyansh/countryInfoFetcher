package com.cgi.countryInfoFetcher.service;

import com.cgi.countryInfoFetcher.exception.CountryNotFoundException;
import com.cgi.countryInfoFetcher.exception.UpstreamApiException;
import com.cgi.countryInfoFetcher.model.Country;
import com.cgi.countryInfoFetcher.model.CountryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

//I am responsible to call the restEndpoint to get the country details and responding back the
// details to the FetchCountriesService.
@Service
public class FetchCountriesApiService {
    private final RestTemplate restTemplate;

    private final String restUrl = "https://restcountries.com/v3.1/alpha/";

    public FetchCountriesApiService() {
        this.restTemplate = new RestTemplate();
    }

    public CountryResponse getCountryByCode(String code) {
        String finalUrl = restUrl + code;
        // Call the external API and fetch the response
        try {
            Country[] countries = restTemplate.getForObject(finalUrl, Country[].class);
            //Fetching the countries and building the response out of it.
            if (countries != null && countries.length > 0) {
                Country country = countries[0];
                return CountryResponse.builder()
                        .countryCode(country.getCca2())
                        .name(country.getName() != null ? country.getName().getOfficial() : null)
                        .capital(country.getCapital() != null && !country.getCapital().isEmpty() ? country.getCapital().get(0) : null)
                        .region(country.getRegion())
                        .currencies(country.getCurrencies() != null ? new ArrayList<>(country.getCurrencies().keySet()) : null)
                        .languages(country.getLanguages() != null ? new ArrayList<>(country.getLanguages().values()) : null)
                        .borders(country.getBorders())
                        .sizeCategory(country.getSizeCategory())
                        .build();
            }
            throw new CountryNotFoundException("Country with code " + code + " not found.");
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new CountryNotFoundException("Country with code " + code + " not found.");
            } else {
                throw new UpstreamApiException("Upstream API failure: " + e.getMessage());
            }
        }
    }
}
