@Feature:
Feature: confirmation

  @confirmation
  Scenario: User with correct credentials can register
    Given 'FIRST' user registers with random data
    When 'FIRST' user confirms email
