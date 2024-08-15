@Login
Feature: Login Functionality to Saucedemo


  Scenario: Login to Sauce Demo and verify you logged in
    Given user is on log in page
    When user inputs  valid username
    And user inputs valid password
    And user clicks on login button
    Then verify user logged in


