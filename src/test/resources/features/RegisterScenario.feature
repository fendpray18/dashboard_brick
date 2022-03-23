@SmokeTest
Feature: Login into Application

  @positiveCase @login
  Scenario Outline: User fill all fields in Register Page
    Given User navigate to Register Page
    When User set all fields <first> <last> <email> <phone> <address> <password> <confpassword>
    Then Success registered into page
    Examples:
    |first |last |email            |phone        |address    |password   |confpassword |
    |test  |aja  |test.satu@dis.com|822138383    |jkt        |qweasd123  |qweasd123    |

  @positiveCase @login
  Scenario: User successfully navigate to Login Page
    Given User navigate to Register Page
    When User click link text Login
    Then Success navigate to Login page

  @negativeCase @login
  Scenario Outline: User sets invalid email
    Given User navigate to Register Page
    When User sets invalid <email>
    Then Show error message invalid email
    Examples:
      |email             |
      |testing.satu@dis  |

  @negativeCase @login
  Scenario Outline: User sets password < 6 char
    Given User navigate to Register Page
    When User sets less six char <password>
    Then Show error message incorrect password
    Examples:
      |password    |
      |12345       |

  @negativeCase @login
  Scenario Outline: User sets different password on confirm password
    Given User navigate to Register Page
    When User sets different confirm <password>
    Then Show error message not match password
    Examples:
      |password   |
      |123  |

  @negativeCase @login
  Scenario: User not input all fields in Register
    Given User navigate to Register Page
    When User directly to click Register Button
    Then Show all error messages