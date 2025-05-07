package com.cgi.countryInfoFetcher.service;

import com.cgi.countryInfoFetcher.exception.CountryNotFoundException;
import com.cgi.countryInfoFetcher.exception.UpstreamApiException;
import com.cgi.countryInfoFetcher.model.CountryResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FetchCountriesServiceTest {
    @Mock
    private FetchCountriesApiService fetchCountriesApiService;

    @InjectMocks
    private FetchCountriesService fetchCountriesService;

    @Test
    void getCountryByCode_returnsCountryResponse_whenCodeIsValid() {
        String validCode = "US";
        CountryResponse mockResponse = CountryResponse.builder()
                .countryCode(validCode)
                .name("United States of America")
                .capital("Washington, D.C.")
                .region("Americas")
                .currencies(List.of("USD"))
                .languages(List.of("English"))
                .borders(List.of("CAN", "MEX"))
                .build();

        when(fetchCountriesApiService.getCountryByCode(validCode)).thenReturn(mockResponse);

        CountryResponse response = fetchCountriesService.getCountryByCode(validCode);

        assertNotNull(response);
        assertEquals(validCode, response.getCountryCode());
        assertEquals("United States of America", response.getName());
    }

    @Test
    void getCountryByCode_throwsCountryNotFoundException_whenCodeIsInvalid() {
        String invalidCode = "XX";

        when(fetchCountriesApiService.getCountryByCode(invalidCode))
                .thenThrow(new CountryNotFoundException("Country not found"));

        assertThrows(CountryNotFoundException.class, () -> fetchCountriesService.getCountryByCode(invalidCode));
    }

    @Test
    void getCountryByCode_throwsUpstreamApiException_whenApiFails() {
        String validCode = "US";

        when(fetchCountriesApiService.getCountryByCode(validCode))
                .thenThrow(new UpstreamApiException("Upstream API error"));

        assertThrows(UpstreamApiException.class, () -> fetchCountriesService.getCountryByCode(validCode));
    }
}
