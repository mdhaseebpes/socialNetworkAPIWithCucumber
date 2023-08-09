Feature: Comments on Posts

  @smoke @regression
  Scenario:User can successfully add a comment on a post
    Given a valid post ID 101 user makes a comment to the post
    Then validating the comments is successfully added to respective post ID 101


  @regression
  Scenario: User can retrieve details of a specific comment
    Given a valid a comment id 30 user makes a Get request
    Then the response body should contain the details of specific comment for id 30


    @regression
    Scenario:User cannot add a comment with wrong endpoint
      Given a invalid endpoint with valid postId 101 makes a comment to the post
      Then validating status error code and comment not added for post
