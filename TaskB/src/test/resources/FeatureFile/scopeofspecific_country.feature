Feature: Predict gender from a single name within a specific country

  Scenario: Predict gender for a known name in the US
    Given the name "Blessy"
    And the country code "US"
    When I call the Genderize API
    Then the response should indicate the gender is "female"
    And the probability should be greater than 0.8

  Scenario: Predict gender for a known name in Germany
    Given the name "Solomon"
    And the country code "DE"
    When I call the Genderize API
    Then the response should indicate the gender is "male"
    And the probability should be greater than 0.8