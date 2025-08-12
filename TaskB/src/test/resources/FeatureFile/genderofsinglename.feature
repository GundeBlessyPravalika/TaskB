Feature: Predict gender from a single name

  Scenario: Predict gender for a known name
    Given the name "Blessy"
    When I call the Genderize API
    Then the response should indicate the gender is "female"
    And the probability should be greater than 0.8
    Then print the API response