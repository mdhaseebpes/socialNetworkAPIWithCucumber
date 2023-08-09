Feature: Posts on Social Network

  @regression @smoke
  Scenario: User can successfully make a post
    Given using valid user account Id user makes a post in social network
    Then validating the post is successfully published with the expected content


  @regression
  Scenario: Get all posts from social Network
    Given retrieve all posts from social Network using Get call
    Then verify user able to fetch all posts and validate successfully


  @regression
  Scenario Outline: Get single post from social Network
    Given retrieve single posts from social Network for <id>
    Then verify user able to fetch single post <id> and validate successfully
    Examples:
      | id  |
      | 100 |


