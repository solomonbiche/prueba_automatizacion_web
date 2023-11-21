Feature: Login Feature

  @test
  Scenario: Search in wikipedia
    Given I open google website
    When  Search in google the word "Automatizacion"
    Then  Enter the wikipedia link about Automatizacion
    When  Find the year of the first automation process
    And   Take a screenshot to the page
