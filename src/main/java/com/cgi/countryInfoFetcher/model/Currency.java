package com.cgi.countryInfoFetcher.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Currency {
    private String symbol;
    private String name;
}
