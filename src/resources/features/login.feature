@SmokeFeature
Feature: feature to test functionallity
	
	@smoketest
  Scenario: check login is succesful with valid credentials
    Given user is in login page
    When user enter username and password
    And click in login button
    Then user is navigated to the home page
