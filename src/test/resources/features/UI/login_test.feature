@smoke
Feature: Login
  Test login

  Background:
    Given user is on the login page

  @web-ui
  Scenario Outline: Test login with wrong credentials
    When the user gives the wrong "<phone_number>" or "<password>"
    Then the user should not be logged in
    And the "Login Failed" prompt should appear
    Examples:
      | phone_number  | password  |
      | 0712345678    | password  |
      | 0722222222    | safaricom |
      | 0711234987    | #$^&*()   |
      | 0799127685    | password  |

  @web-ui
  Scenario Outline: Test login with missing credentials
      When the user gives the wrong "<phone_number>" or "<password>"
      Then there should be a prompt for the missing entry
      Examples:
        | phone_number  | password  |
        | 0712345678    |           |
        |               | safaricom |

  @web-ui
  Scenario: Test login with correct credentials
    When the user gives the correct "phone_number" and "password"
    Then the user should be logged in and the profile button should be visible

    # Enter steps here