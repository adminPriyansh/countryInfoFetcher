package com.cgi.countryInfoFetcher.builder;


import com.cgi.countryInfoFetcher.model.Country;
import com.cgi.countryInfoFetcher.model.CountryResponse;

import java.util.ArrayList;

public class CountryResponseBuilder {

    public static CountryResponse buildResponse(Country country){
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

}
