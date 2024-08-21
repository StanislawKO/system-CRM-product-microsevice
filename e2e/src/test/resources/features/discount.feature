Feature: Discount Management

  Scenario: Retrieve all discounts
    Given the discount service is running
    When I request all discounts
    Then I should receive a list of discounts

  Scenario: Retrieve a discount by ID
    Given the discount service is running
    When I request a discount with ID 1
    Then I should receive the discount with ID 1

  Scenario: Create a new discount
    Given the discount service is running
    When I create a new discount with percentage 10
    Then the discount should be created successfully

  Scenario: Update an existing discount
    Given the discount service is running
    When I update the discount with ID 1 to have percentage 20
    Then the discount should be updated successfully

  Scenario: Delete a discount
    Given the discount service is running
    When I delete the discount with ID 1
    Then the discount should be deleted successfully
