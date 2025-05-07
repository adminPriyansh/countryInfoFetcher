package com.cgi.countryInfoFetcher.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NativeName {
    private String official;
    private String common;
}