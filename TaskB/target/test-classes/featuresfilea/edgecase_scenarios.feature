Feature: Genderize API - Edge Case Handling

  Background:
    Given I will set the Genderize.io base URI to "https://api.genderize.io"

  Scenario: Checking for the missing name parameter
    When I send GET request without name parameter
    Then the response status code should be 422
    And the error message should be "Missing 'name' parameter"

  Scenario: Checking the  Invalid name parameter (empty value)
    When I send GET request with name parameter as empty
    Then the response status code should be 422
    And the error message should be "Invalid 'name' parameter"