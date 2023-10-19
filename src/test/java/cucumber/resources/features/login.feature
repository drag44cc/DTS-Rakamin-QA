Feature: Login to Sauce Demo

  @Regression @Positive @ALL
  Scenario: User logs in with valid credentials
    Given the user is on the Sauce Demo login page
    When the user enter valid username
    And the user enter valid password
    And the user click on the login button
    Then the user logged in to the Sauce Demo application

  @Regression @Negative @ALL
  Scenario: User logs in with invalid credentials
    Given the user is on the Sauce Demo login page
    When the user enter invalid username
    And the user enter valid password
    And the user click on the login button
    Then the user see an error message that says

  @TDD @ALL
  Scenario Outline: User logs in with valid credentials TDD
    Given the user is on the Sauce Demo login page
    When the user enter <username> username TDD
    And the user enter <password> password TDD
    And the user click on the login button
    Then the user <status> logged in to the Sauce Demo application
    Examples:
      | username         | password     | status  |
      | standard_user    | secret_sauce | success |
      | invalid_username | secret_sauce | failed  |