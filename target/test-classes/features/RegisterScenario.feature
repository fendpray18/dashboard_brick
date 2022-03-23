@SmokeTest
Feature: Login into Application

  @positiveCase @login
  Scenario Outline: User input correct credential as request
    Given User inputs the base API request <URL>
    When User sets credential <email> <password> to send the API request
    Then Validate the response status is 200
    And Show body response
    Examples:
    |URL                 |email             | password        |
    |https://reqres.in   |eve.holt@reqres.in| cityslicka      |

  @negativeCase @login
  Scenario Outline: User input wrong email as request
    Given User inputs the base API request <URL>
    When User sets incorrect email on <email> <password> to send the API request
    Then Validate the response status is 400
    And Show body response
    Examples:
      |URL                 |email        | password        |
      |https://reqres.in   |exampletest  | cityslicka      |

  @negativeCase @login
  Scenario Outline: User input wrong password as request
    Given User inputs the base API request <URL>
    When User sets incorrect password on <email> <password> to send the API request
    Then Validate the response status is 400
    And Show body response
    Examples:
      |URL                 |email             | password        |
      |https://reqres.in   |eve.holt@reqres.in| mosalah         |