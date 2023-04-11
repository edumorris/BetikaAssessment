@api
Feature: API Check Users
  Check whether users exists

  Scenario: Check if User 6 exists.
    Given a request is made to the endpoint
    Then a 200 response should be received
    And the "User 6" should exist