package com.cgi.countryInfoFetcher.controller;

import com.cgi.countryInfoFetcher.exception.InvalidCodeFormatException;
import com.cgi.countryInfoFetcher.model.CountryResponse;
import com.cgi.countryInfoFetcher.service.FetchCountriesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FetchCountriesControllerTest {
    @Mock
    private FetchCountriesService fetchCountriesService;

    @InjectMocks
    private FetchCountriesController fetchCountriesController;

    @Test
    void getCountryByCode_returnsCountryResponse_whenCodeIsValid() {
        String validCode = "US";
        CountryResponse mockResponse = CountryResponse.builder()
                .countryCode("US")
                .name("United States of America")
                .capital("Washington, D.C.")
                .region("Americas")
                .currencies(List.of("USD"))
                .languages(List.of("English"))
                .borders(List.of("CAN", "MEX"))
                .build();

        when(fetchCountriesService.getCountryByCode(validCode)).thenReturn(mockResponse);

        ResponseEntity<CountryResponse> response = fetchCountriesController.getCountryByCode(validCode);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
    }

    @Test
    void getCountryByCode_throwsInvalidCodeFormatException_whenCodeIsInvalid() {
        String invalidCode = "USA";

        InvalidCodeFormatException exception = assertThrows(
                InvalidCodeFormatException.class,
                () -> fetchCountriesController.getCountryByCode(invalidCode)
        );

        assertEquals("Invalid country code format", exception.getMessage());
    }

    @Test
    void getCountryByCode_throwsInvalidCodeFormatException_whenCodeIsEmpty() {
        String emptyCode = "";

        Assertions.assertThrows(InvalidCodeFormatException.class , () -> fetchCountriesController.getCountryByCode(emptyCode));
    }

    @Test
    void getCountryByCode_throwsInvalidCodeFormatException_whenCodeContainsLowercaseLetters() {
        String invalidCode = "us";

        InvalidCodeFormatException exception = assertThrows(
                InvalidCodeFormatException.class,
                () -> fetchCountriesController.getCountryByCode(invalidCode)
        );

        assertEquals("Invalid country code format", exception.getMessage());
    }
}

