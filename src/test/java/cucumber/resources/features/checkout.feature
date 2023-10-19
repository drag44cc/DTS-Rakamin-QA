Feature: Check out

  @checkoutPositive @ALL
  Scenario: Checkout with valid information
    Given The User logged in to the Sauce Demo application with valid credentials
    When The User add the 'Sauce Labs Backpack' item to the cart
    And The User click on the cart icon then 'Checkout' button
    And The User enter shipping information
    And The User click on the 'Continue' button
    And The User see Checkout: Overview then click 'Finish' button
    Then The User see a confirmation message that says 'Thank you for your order!'

  @checkoutNegative @ALL
  Scenario: Checkout with invalid information
    Given The User logged in to the Sauce Demo application with valid credentials
    When The User add the 'Sauce Labs Backpack' item to the cart
    And The User click on the cart icon then 'Checkout' button
    And The User enter invalid shipping information
    And The User click on the 'Continue' button
    Then The User see an error message that says 'Error: First Name is required'