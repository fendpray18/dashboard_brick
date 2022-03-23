@SmokeTest
Feature: Login into Application

  @positiveCase @login
  Scenario: User successfully logged in
    Given User navigate to Login Page
    When User input correct credential
    Then Success login into page

  @positiveCase @login
  Scenario: User successfully logged out
    Given User navigate to Login Page
    When User input correct credential
    Then Success login into page
    And Success to logged out

  @positiveCase @login
  Scenario: User successfully navigate to Sign Up Page
    Given User navigate to Login Page
    When User click link text Sign Up
    Then Success navigate to sign up page

  @negativeCase @login
  Scenario Outline: User failed to login because of incorrect email
    Given User navigate to Login Page
    When User input incorrect <email> email
    Then Show popup incorrect credential
    Examples:
      |email                 |
      |testing.satu@dis.com  |

  @negativeCase @login
  Scenario Outline: User failed to login because of incorrect password
    Given User navigate to Login Page
    When User input incorrect <password> password
    Then Show popup incorrect credential
    Examples:
      |password        |
      |qweasd123       |