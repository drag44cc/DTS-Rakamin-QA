Feature: Add to cart
  Scenario: Add item to cart
    Given The user logged in to the Sauce Demo application with valid credentials
    When The user click on the 'Add to cart' button for the 'Sauce Labs Backpack' item
    Then the item is added to the cart