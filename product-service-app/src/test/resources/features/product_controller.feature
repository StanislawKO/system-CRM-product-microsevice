Feature: Product Controller

  Scenario: Get product by ID
    Given the product service is running
    And a product exists
    When I send a GET request to "/v1/products/{id}"
    Then the response status should be 200
    And the response should contain the created product ID

  Scenario: Get all products
    Given the product service is running
    When I send a GET request to all products "/v1/products"
    Then the response status should be 200
    And the response should contain a list of products

  Scenario: Create a new product
    Given the product service is running
    When I send a POST request to "/v1/products" with the product data
    Then the response status should be 201

  Scenario: Update a product
    Given the product service is running
    And a product exists
    When I send a PUT request to "/v1/products/{id}" with the updated product data
    Then the response status should be 200

  Scenario: Delete a product by ID
    Given the product service is running
    And a product exists
    When I send a DELETE request to "/v1/products/{id}"
    Then the response status should be 200
