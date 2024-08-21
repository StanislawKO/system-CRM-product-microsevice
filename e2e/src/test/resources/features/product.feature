Feature: Product Management

  Scenario: Retrieve all products
    Given the product service is running
    When I request all products
    Then I should receive a list of products

  Scenario: Retrieve a product by ID
    Given the product service is running
    When I request a product with ID 1
    Then I should receive the product with ID 1

  Scenario: Create a new product
    Given the product service is running
    When I create a new product with name "Test Product" and price 100
    Then the product should be created successfully

  Scenario: Update an existing product
    Given the product service is running
    When I update the product with ID 1 to have name "Updated Product" and price 150
    Then the product should be updated successfully

  Scenario: Delete a product
    Given the product service is running
    When I delete the product with ID 1
    Then the product should be deleted successfully

#  Scenario: Get all products
#    Given the product service is running
#    When I send a GET request to products
#    Then the response status should be 200
#    And the response should contain a list of products
#
#  Scenario: Create a product
#    Given a product with summary "Java", description "Course", price 45.0, duration 5 and active true
#    When the product is created
#    Then the product should be saved and not null
#
#  Scenario: Update a product
#    Given a product with summary "Java", description "Course", price 45.0, duration 5 and active true
#    When the product is created
#    And the product is updated with duration 10
#    Then the product duration should be 10
#
#  Scenario: Delete a product
#    Given a product with summary "Java", description "Course", price 45.0, duration 5 and active true
#    When the product is created
#    And the product is deleted
#    Then the product should be null
#
#  Scenario: Update product price
#    Given a product with summary "Java", description "Course", price 45.0, duration 5 and active true
#    When the product is created
#    And the product price is updated to 50.0
#    Then the product price should be 50.0

#  Scenario: Get product by ID
#    Given the product with ID 1 exists
#    When I request the product with ID 1
#    Then the response status should be 200
#    And the response should contain the product details