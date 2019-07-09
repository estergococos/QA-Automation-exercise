@exercise1
@facebook
@signUp
Feature: Sign up in facebook
  As a user I can login in facebook

  Background:
    Given I go to "https://www.facebook.com"

  Scenario Outline: Sign up with a valid data
    Given I fill all the mandatory data with "<gender>"
    When I select "Sign Up" button
    Then I am on the "Confirm email" page

    Examples:
      | gender |
      | male   |
      | female |

  Scenario: Repeat email field doesn't appear at the beginning
    Then Repeat email field is not showed

  Scenario: Repeat email field doesn't appear with incorrect data
    When I fill email field with incorrect data
    Then Repeat email field is not showed

  Scenario: Repeat email field appears
    When I fill email field with correct data
    Then Repeat email field is showed

  Scenario: Introduce an invalid email
    Given I fill all the mandatory data with invalid email
    When I move the cursor
    Then Email field is in red

  Scenario: Try to sign up with an invalid email
    Given I fill all the mandatory data with invalid email
    When I select "Sign Up" button
    Then "Wrong email" error message appears

  Scenario: Try to sign up with an invalid birth date
    Given I fill all the mandatory data with invalid birth date
    When I select "Sign Up" button
    Then "Wrong birth date" error message appears

  Scenario Outline: Sign up with a custom gender
    Given I fill all the mandatory data with "<pronoun>" as a pronoun of custom gender
    When I select "Sign Up" button
    Then I am on the "Confirm email" page

    Examples:
      | pronoun |
      | she     |
      | he      |
      | They    |

  Scenario Outline: Try to sign up with an empty field
    Given I fill all the mandatory data with "<field>" empty
    When I select "Sign Up" button
    Then "Empty <field>" error message appears

    Examples:
      | field        |
      | First Name   |
      | Last Name    |
      | email        |
      | repeat email |
      | password     |
      | gender       |
