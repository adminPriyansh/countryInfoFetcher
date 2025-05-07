# Country Info Fetcher

## Overview
The **Country Info Fetcher** is a Spring Boot application that provides country information by consuming data from an external REST API (https://restcountries.com/v3.1/alpha/{code}). It validates country codes, fetches details, and returns a structured response.

## Features
- Fetch country details by country code.
- Validates country code format (must be two uppercase letters).
- Handles upstream API errors and provides meaningful exceptions.
- Uses the Builder pattern for creating response objects.

## Project Structure
### Controllers
- **`FetchCountriesController`**: Handles HTTP requests and validates the country code format.

### Services
- **`FetchCountriesService`**: Calls the API service to fetch country details.
- **`FetchCountriesApiService`**: Interacts with the external REST API to retrieve country data.

### Models
- **`CountryResponse`**: Represents the response structure for country details.
- **`Currency`**: Represents currency details.
- **`Name`**: Represents the official and common names of a country.
- **`Capital`**: Represents the capital name and timezone.

### Exception Handling
- **`InvalidCodeFormatException`**: Thrown when the country code format is invalid.
- **`CountryNotFoundException`**: Thrown when the country is not found in the external API.
- **`UpstreamApiException`**: Thrown when the external API fails.

## Dependencies
- **Spring Boot**: For building the RESTful web service.
- **Lombok**: For reducing boilerplate code (e.g., `@Builder`, `@Data`).
- **MockRestServiceServer**: For mocking external API calls in tests.

## Example Usage
### Fetch Country by Code
Endpoint: `GET '/countries/NE'

#### Request
curl -X GET http://localhost:8080/countries/US
