@exercise2
@amazon
@shoppingCart
Feature: Modify Amazon Shopping Cart
  As a user I can add an article to Shopping cart and ten I can modify the number of products. Total price is modified correctly

  Scenario Outline: Add different kind of products and edit one of them
    Given I go to "https://www.amazon.com"
    And I search for "hats for men" in home page
    When I add first result to cart with quantity "<men hats>"
    Then Products appears on shopping cart and price is correct
    Given I search for "hats for women" in home page
    When I add first result to cart with quantity "<women hats>"
    Then Products appears on shopping cart and price is correct
    When I change the quantity of "<option>" for "<new value>"
    Then Products appears on shopping cart and price is correct

    Examples: #I performed this test as a Scenario Outline because is a test than can be executed with different parameters.
    #The first row is according to test specifications; other rows are just additional testing
      | men hats | women hats | option | new value |
      | 2        | 1          | 1      | 1         |
      | 2        | 3          | 1      | 5         |
      | 2        | 2          | 2      | 1         |
      | 1        | 3          | 2      | 4         |

#    I don't like the gherkin tests that mix When steps and Then. In my opinion, a When step never will appear after any Then step.

#  In this case, I create 3 test cases using the same stepdefs or gherkin sentences:

#    Scenario: Add one product to Shopping Cart
#    Given I go to "https://www.amazon.com"
#    And I search for "hats for men" in home page
#    When I add first result to cart with quantity "2"
#    Then Products appears on shopping cart and price is correct
#
#  Scenario: Add two product to Shopping Cart
#    Given I go to "https://www.amazon.com"
#    And I search for "hats for men" in home page
#    And I add first result to cart with quantity "2"
#    And I search for "hats for women" in home page
#    When I add first result to cart with quantity "1"
#    Then Products appears on shopping cart and price is correct
#
#  Scenario: Modify the Shopping Cart with more than one product
#    Given I go to "https://www.amazon.com"
#    And I search for "hats for men" in home page
#    And I add first result to cart with quantity "2"
#    And I search for "hats for women" in home page
#    When I add first result to cart with quantity "1"
#    When I change the number of "men hats" for "1"
#    Then Products appears on shopping cart and price is correct
