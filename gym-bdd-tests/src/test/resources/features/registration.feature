@Feature:
Feature: registration

  @registration
  Scenario: User with correct credentials can register providing his gender
    Given valid 'FIRST' user registration data
    When 'FIRST' user registers with valid data with gender

  @registration
  Scenario: User with correct credentials can register without providing his gender
    Given valid 'FIRST' user registration data
    When 'FIRST' user registers with valid data without gender

  @registration
  Scenario: User with correct credentials cannot register providing different passwords
    Given valid 'FIRST' user registration data
    When 'FIRST' user registers with different passwords and receives <100000> error code

  @registration
  Scenario: User with correct credentials cannot register without providing his mail
    Given valid 'FIRST' user registration data
    When 'FIRST' user registers with valid data without mail and receives <100010> error code

  @registration
  Scenario: User with correct credentials cannot register without providing his nickname
    Given valid 'FIRST' user registration data
    When 'FIRST' user registers with valid data without nickname and receives <100020> error code

  @registration
  Scenario: User with incorrect password cannot register successfully
    Given valid 'FIRST' user registration data
    When 'FIRST' user registers with invalid password and receives <100030> error code

  @registration
  Scenario: User with invalid gender cannot register successfully
    Given valid 'FIRST' user registration data
    When 'FIRST' user registers with invalid gender and receives <100040> error code

  @registration
  Scenario: User with correct credentials cannot register twice providing his gender
    Given valid 'FIRST' user registration data
    When 'FIRST' user registers with valid data with gender
    And 'FIRST' user registers with valid data with gender and receives <100050> error code

  @registration
  Scenario: User with correct credentials cannot register twice not providing his gender
    Given valid 'FIRST' user registration data
    When 'FIRST' user registers with valid data without gender
    And 'FIRST' user registers with valid data without gender and receives <100050> error code

  @registration
  Scenario: User with correct credentials cannot register once providing his gender and once not providing his gender
    Given valid 'FIRST' user registration data
    When 'FIRST' user registers with valid data with gender
    And 'FIRST' user registers with valid data without gender and receives <100050> error code
