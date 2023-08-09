Feature: Users on Social Network

  @regression
  Scenario: User can retrieve a list of all users
    When the user requests to get all users
    Then verify response status code should be 200 and response time
    And the list of users should be retrieved successfully

  @regression @smoke
  Scenario: User can retrieve details of a specific user
    Given a valid user ID 10 and make a get request
    Then verify response status code should be 200 and response time
    And the response body should contain the details of the user with ID 10

  @regression
  Scenario: User attempts to retrieve details of an invalid user
    Given an invalid user ID 1001 and make a get request
    Then the response should not found with correct status code


