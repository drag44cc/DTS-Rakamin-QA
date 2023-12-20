Feature: Logout

  @Logout @ALL
  Scenario: Logout
    Given The User logged in to the Sauce Demo application
    When The User click on the Logout button
    Then The User logged out of the Sauce Demo application