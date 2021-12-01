Feature: Test login functionality

  Scenario: Check login is succesful with valid credentials
    Given Browser is open
    And user is on login page
    When user enter username and password
    And user clicks on login button
    Then the page reacts according to the case:
