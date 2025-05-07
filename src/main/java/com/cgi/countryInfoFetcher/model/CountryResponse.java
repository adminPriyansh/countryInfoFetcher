package com.cgi.countryInfoFetcher.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CountryResponse {
    private String countryCode;
    private String name;
    private String capital;
    private String region;
    private List<String> currencies;
    private List<String> languages;
    private List<String> borders;
    private String sizeCategory;

}
