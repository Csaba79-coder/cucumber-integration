Feature: User Registration

  Scenario: User registers successfully with valid credentials
    Given the user is on the registration page
    When the user enters valid username "testuser" and password "password123"
    Then the user should be registered successfully

  Scenario: User tries to register with an existing username
    Given the user is on the registration page
    When the user enters existing username "existinguser" and password "password123"
    Then the user should see an error message indicating that the username already exists
