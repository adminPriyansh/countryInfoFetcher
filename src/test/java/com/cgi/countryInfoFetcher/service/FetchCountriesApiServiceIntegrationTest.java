package com.cgi.countryInfoFetcher.service;

import com.cgi.countryInfoFetcher.exception.CountryNotFoundException;
import com.cgi.countryInfoFetcher.model.CountryResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

@SpringBootTest
public class FetchCountriesApiServiceIntegrationTest {

    @Autowired
    private FetchCountriesApiService fetchCountriesApiService;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @Test
    void testGetCountryByCode_Success() {
        mockServer = MockRestServiceServer.createServer(restTemplate);

        String countryCode = "US";
        String mockResponse = """
                [
                    {
                        "cca2": "US",
                        "name": { "official": "United States of America" },
                        "capital": ["Washington, D.C."],
                        "region": "Americas",
                        "currencies": { "USD": {} },
                        "languages": { "eng": "English" },
                        "borders": ["CAN", "MEX"],
                        "sizeCategory": "Large"
                    }
                ]
                """;

        mockServer.expect(requestTo("https://restcountries.com/v3.1/alpha/" + countryCode))
                .andRespond(withSuccess(mockResponse, MediaType.APPLICATION_JSON));

        CountryResponse response = fetchCountriesApiService.getCountryByCode(countryCode);

        assertNotNull(response);
        assertEquals("US", response.getCountryCode());
        assertEquals("United States of America", response.getName());
        assertEquals("Washington, D.C.", response.getCapital());
        assertEquals("Americas", response.getRegion());
        assertTrue(response.getCurrencies().contains("USD"));
        assertTrue(response.getLanguages().contains("English"));
        assertTrue(response.getBorders().contains("CAN"));
        assertTrue(response.getBorders().contains("MEX"));
    }

    @Test
    void testGetCountryByCode_NotFound() {
        mockServer = MockRestServiceServer.createServer(restTemplate);

        String countryCode = "XX";

        mockServer.expect(requestTo("https://restcountries.com/v3.1/alpha/" + countryCode))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

        assertThrows(CountryNotFoundException.class, () -> fetchCountriesApiService.getCountryByCode(countryCode));
    }
}
