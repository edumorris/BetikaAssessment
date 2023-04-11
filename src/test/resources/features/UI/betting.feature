@web-ui
Feature: Betting
  Tests for betting functionality

  Background:
    Given user is on the login page
    And the user gives the correct "phone_number" and "pwd"
    When the user access the "soccer_matches_subdirectory"

  @regression
  Scenario: Matches are displayed
    Then there should be matches displayed

  @smoke
  Scenario: Checking bet slip
    And the user selects a number of bets
    Then the selected bets should appear on the bet slip area