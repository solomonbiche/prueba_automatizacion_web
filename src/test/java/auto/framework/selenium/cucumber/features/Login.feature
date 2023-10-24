Feature: Login Feature

  @test
  Scenario: Search in wikipedia
    Given I open google website
    When  Search in google the word "Automatizacion"
    Then  Enter the wikipedia link about Automatizacion
    When  Find the year of the first automation process
    And   Take a screenshot to the page


#  Scenario Outline: I login the website with empty username and empty password
#    Given I am on the <endpoint> login page
#    When I try to login with <credential> credentials
#    Then I verify invalid login message
#    Examples:
#      |endpoint    | credential   |
#      |otto_drivers| otto_driver_valid   |
#      |            | otto_invalid |
