
@Feature:
Feature: logging in

  @logging
  Scenario: User with correct credentials can register
    Given 'FIRST' user registers with random data
    Given user uses 'FIRST' user valid login data and loggs in