Feature: Add to cart

  @runAddtocartPositive @ALL
  Scenario: Add item to cart
    Given The user logged in to the Sauce Demo application with valid credentials
    When The user click on the 'Add to cart' button for the 'Sauce Labs Backpack' item
    Then the item is added to the cart

  @runAddtocartNegative @ALL
  Scenario: button remove item from cart that is not working
    Given The user logged in to the Sauce Demo application with error account
    When The user click on the 'Add to cart' button for the 'Sauce Labs Backpack' item
    And I click on the button remove
    Then I see an button remove function is not working