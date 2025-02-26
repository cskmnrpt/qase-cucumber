Feature: Google Search

  @selenium
  Scenario: Search on Google
    Given I open Google
    Given I search for "Selenium Cucumber"


  @selenium
  Scenario: Failing Google Search Test
    Given I open Google
    Given I search for "Selenium Cucumber"
    Given I fail the test on purpose
