@Feature:
Feature: logging in

  @logging
  Scenario: Confirmed user can login successfully
    Given 'FIRST' user registers with random data
    When 'FIRST' user confirms email
    And user uses 'FIRST' user valid login data and loggs in

  @logging
  Scenario: Not confirmed user cannot log in
    Given 'FIRST' user registers with random data
    When 'FIRST' not confirmed user tries to log in and receive <100080> error code

  @logging
  Scenario: Not created user cannot log in
    Given valid 'FIRST' user registration data
    When 'FIRST' not confirmed user tries to log in and receive <100075> error code
