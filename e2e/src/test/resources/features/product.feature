Feature: Product management

  Scenario: Get product by ID
    Given the product with ID 1 exists
    When I request the product with ID 1
    Then the response status should be 200
    And the response should contain the product details