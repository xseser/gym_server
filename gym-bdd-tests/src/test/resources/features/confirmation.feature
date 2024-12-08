@Feature:
Feature: confirmation

  @confirmation
  Scenario: User with correct credentials can register
    Given 'FIRST' user registers with random data
    When 'FIRST' user confirms email

  @confirmation
  Scenario: User cannot confirm personalisation with invalid secret value
    Given 'FIRST' user registers with random data
    When 'FIRST' user confirms his email with invalid secret and receives <200000> code
