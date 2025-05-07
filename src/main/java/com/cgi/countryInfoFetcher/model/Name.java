package com.cgi.countryInfoFetcher.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class Name {
    private String common;
    private String official;
    private Map<String, NativeName> nativeName;
}
