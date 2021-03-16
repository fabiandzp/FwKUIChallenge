Feature: Login
  Scenario: Successful Log In
    Given the user wants to log in to his account
    When the user submits his credentials
    Then the user should be able to log in

  Scenario: Unsuccessful Log in
    Given the user wants to log in to his account
    But the user has incorrect credentials
    When the user submits his credentials
    Then the user should see an error message
    And the user should not be able to log in

