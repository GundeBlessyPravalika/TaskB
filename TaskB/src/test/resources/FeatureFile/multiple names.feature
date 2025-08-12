Feature: Genderize API - Batch gender detection

  Scenario Outline: Predict gender for multiple names
    Given I have the following names:
      | name        |
      | Solomon     |
      | Alexandra   |
      | David       |
    When I send a batch request to the Genderize.io API
    Then the response should contain all provided names
    And each entry should include gender, probability, and count

    