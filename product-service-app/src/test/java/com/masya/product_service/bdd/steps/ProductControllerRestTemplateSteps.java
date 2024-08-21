package com.masya.product_service.bdd.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.masya.product_service.web.dto.ProductDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@CucumberContextConfiguration
public class ProductControllerRestTemplateSteps {

    private final TestRestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private ResponseEntity<String> response;
    private Long createdProductId;
    private final String baseUrl = "http://localhost:8080";

    public ProductControllerRestTemplateSteps() {
        this.restTemplate = new TestRestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    @Given("the product service is running")
    public void theProductServiceIsRunning() {
        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "/actuator/health", String.class);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Given("a product exists")
    public void aProductExists() throws Exception {
        ProductDto product = ProductDto.builder()
                .summary("Test Product")
                .description("Test Description")
                .price(new BigDecimal("100.0"))
                .duration((short) 30)
                .discountId(1L)
                .active(true)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(product), headers);

        try {
            ResponseEntity<ProductDto> response = restTemplate.postForEntity(baseUrl + "/v1/products", request, ProductDto.class);
            assertEquals(201, response.getStatusCodeValue());
            // Сохраняем ID созданного продукта
            createdProductId = response.getBody().getId();
        } catch (HttpClientErrorException e) {
            System.out.println("Error response body: " + e.getResponseBodyAsString());
            throw e;
        }
    }

    @When("I send a GET request to {string}")
    public void iSendAGETRequestTo(String url) {
        try {
            response = restTemplate.getForEntity(baseUrl + url.replace("{id}", createdProductId.toString()), String.class);
        } catch (HttpClientErrorException e) {
            response = new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
        }
    }

    @When("I send a POST request to {string} with the product data")
    public void iSendAPOSTRequestToWithTheProductData(String url) throws Exception {
        ProductDto productDto = ProductDto.builder()
                .summary("Test Product")
                .description("Test Description")
                .price(new BigDecimal("100.0"))
                .duration((short) 30)
                .discountId(1L)
                .active(true)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(productDto), headers);
        response = restTemplate.postForEntity(baseUrl + url, request, String.class);
    }

    @When("I send a PUT request to {string} with the updated product data")
    public void iSendAPUTRequestToWithTheUpdatedProductData(String url) throws Exception {
        ProductDto updatedProduct = ProductDto.builder()
                .summary("Updated Product")
                .description("Updated Description")
                .price(new BigDecimal("200.0"))
                .duration((short) 60)
                .discountId(1L)
                .active(true)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(updatedProduct), headers);

        try {
            response = restTemplate.exchange(baseUrl + url.replace("{id}", createdProductId.toString()), HttpMethod.PUT, request, String.class);
        } catch (HttpClientErrorException e) {
            System.out.println("Error response body: " + e.getResponseBodyAsString());
            throw e;
        }
    }

    @When("I send a DELETE request to {string}")
    public void iSendADELETERequestTo(String url) {
        response = restTemplate.exchange(baseUrl + url.replace("{id}", createdProductId.toString()), HttpMethod.DELETE, null, String.class);
    }

    @When("I send a GET request to all products {string}")
    public void iSendAGETRequestToAllProducts(String url) {
        System.out.println("Sending GET request to: " + url);
        try {
            response = restTemplate.getForEntity(baseUrl + url, String.class);
            System.out.println("GET request response: " + response.getBody());
        } catch (HttpClientErrorException e) {
            System.out.println("GET request error response: " + e.getResponseBodyAsString());
            response = new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
        }
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int statusCode) {
        assertEquals(statusCode, response.getStatusCodeValue());
    }

    @Then("the response should contain the created product ID")
    public void theResponseShouldContainTheCreatedProductID() throws Exception {
        assertNotNull(response.getBody());
        ProductDto product = objectMapper.readValue(response.getBody(), ProductDto.class);
        assertEquals(createdProductId, product.getId());
    }

    @Then("the response should contain a list of products")
    public void theResponseShouldContainAListOfProducts() throws Exception {
        System.out.println("Checking response body for list of products");
        assertNotNull(response.getBody());
        ProductDto[] products = objectMapper.readValue(response.getBody(), ProductDto[].class);
        System.out.println("Products in response: " + products.length);
        assertEquals(true, products.length > 0);
    }

}

