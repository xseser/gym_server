@Feature:
Feature: confirmation

  @confirmation
  Scenario: User with correct credentials can register and confirm his mail
    Given 'FIRST' user registers with random data
    When 'FIRST' user confirms email

  @confirmation
  Scenario: User confirms his mail twice
    Given 'FIRST' user registers with random data
    When 'FIRST' user confirms email twice and at the second time receives <200000> code

  @confirmation
  Scenario: User cannot confirm personalisation with invalid secret value
    Given 'FIRST' user registers with random data
    When 'FIRST' user confirms his email with invalid secret and receives <200000> code
