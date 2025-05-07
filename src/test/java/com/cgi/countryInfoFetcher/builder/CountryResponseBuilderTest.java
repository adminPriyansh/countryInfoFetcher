package com.cgi.countryInfoFetcher.builder;

import com.cgi.countryInfoFetcher.model.Country;
import com.cgi.countryInfoFetcher.model.CountryResponse;
import com.cgi.countryInfoFetcher.model.Currency;
import com.cgi.countryInfoFetcher.model.Name;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CountryResponseBuilderTest {


    @Test
    void buildResponse_returnsCountryResponse_whenCountryHasAllFields() {
        Country country = Country.builder()
                .cca2("US")
                .name(Name.builder().official("United States of America").build())
                .capital(List.of("Washington, D.C."))
                .region("Americas")
                .currencies(Map.of("USD", Currency.builder().name("United States dollar").build()))
                .languages(Map.of("eng", "English"))
                .borders(List.of("CAN", "MEX"))
                .population(10_000_001)
                .build();

        CountryResponse response = CountryResponseBuilder.buildResponse(country);

        assertNotNull(response);
        assertEquals("US", response.getCountryCode());
        assertEquals("United States of America", response.getName());
        assertEquals("Washington, D.C.", response.getCapital());
        assertEquals("Americas", response.getRegion());
        assertTrue(response.getCurrencies().contains("USD"));
        assertTrue(response.getLanguages().contains("English"));
        assertTrue(response.getBorders().contains("CAN"));
        assertEquals("LARGE", response.getSizeCategory());
    }

    @Test
    void buildResponse_returnsCountryResponseWithNullFields_whenCountryHasMissingFields() {
        Country country = Country.builder()
                .cca2("US")
                .name(null)
                .capital(null)
                .region(null)
                .currencies(null)
                .languages(null)
                .borders(null)
                .population(10000)
                .build();
        country.setCca2("US");
        country.setName(null);
        country.setCapital(null);
        country.setRegion(null);
        country.setCurrencies(null);
        country.setLanguages(null);
        country.setBorders(null);

        CountryResponse response = CountryResponseBuilder.buildResponse(country);

        assertNotNull(response);
        assertEquals("US", response.getCountryCode());
        assertNull(response.getName());
        assertNull(response.getCapital());
        assertNull(response.getRegion());
        assertNull(response.getCurrencies());
        assertNull(response.getLanguages());
        assertNull(response.getBorders());
    }
}
