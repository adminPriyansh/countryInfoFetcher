package com.cgi.countryInfoFetcher.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country {
    private Name name;
    private List<String> tld;
    private String cca2;
    private Map<String, Currency> currencies;
    private List<String> capital;
    private String region;
    private Map<String, String> languages;
    private List<String> borders;
    private long population;


    public String getSizeCategory() {
        if (population < 1_000_000) {
            return "SMALL";
        } else if (population <= 10_000_000) {
            return "MEDIUM";
        } else {
            return "LARGE";
        }
    }
}
