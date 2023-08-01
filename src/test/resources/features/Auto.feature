@Automation @Google

Feature: The user find a word in google and check the result in a wikipedia page, then take a screenshot

  @FirstSearch
  Scenario: The user find a word in google and check the result in a wikipedia page
    Given The next URL: "https://www.google.com"
    When The user find "automatización" in the search
    And select the wikipedia Link
    Then The system displays the first automation in the "1785"
    And take a screenshot

