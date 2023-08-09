package com.social.steps;

import com.social.modules.CommentsModule;
import com.social.pojo.MainComments;
import com.social.resource.RestResource;
import com.social.resource.SpecBuilder;
import com.social.utils.Constants;
import com.social.utils.FileLocations;
import com.social.utils.PropertyInit;
import com.social.utils.Status;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CommentSteps {

   private Response response = null;
    Properties properties = PropertyInit.propertyLoader(FileLocations.DATA_DIRECTORY);


    @Given("a valid post ID {int} user makes a comment to the post")
    public void aValidPostIDUserMakesACommentToThePost(int postId) {
        //serialization using pojo  classes
        MainComments mainComments = new MainComments(postId,
                properties.getProperty("name"),
                properties.getProperty("email"), properties.getProperty("body"));
        response = RestResource.post(mainComments, Constants.COMMENTS_ENDPOINT);

    }

    @Then("validating the comments is successfully added to respective post ID {int}")
    public void validatingTheCommentsIsSuccessfullyAddedToRespectivePostID(int postId) {
        //Deserialize concept using pojo classes
        MainComments actualResponse = response.as(MainComments.class);
        assertThat(response.getStatusCode(),equalTo(Status.CREATED_201));

        //verifying the response time of api
        assertThat(SpecBuilder.responseTimeValidation(response, properties.getProperty("response")), Matchers.is(true));

        assertThat(actualResponse.getEmail(), equalTo( properties.getProperty("email")));
        assertThat(actualResponse.getName(), equalTo(properties.getProperty("name")));
        assertThat(actualResponse.getPostId(), is(postId));
        assertThat(actualResponse.getId(), is(notNullValue()));

    }

    @Given("a invalid endpoint with valid postId {int} makes a comment to the post")
    public void aInvalidEndpointWithValidPostIdMakesACommentToThePost(int postId) {
        //serialize using pojo  classes
        MainComments mainComments = new MainComments(postId,
                properties.getProperty("name"),
                properties.getProperty("email"), properties.getProperty("body"));
        response = RestResource.post(mainComments, properties.getProperty("basePathInvalid"));
    }


    @Then("validating status error code and comment not added for post")
    public void validatingStatusErrorCodeAndCommentNotAddedForPost() {
        assertThat(response.getStatusCode(), equalTo(Status.NOT_FOUND_ERROR));
    }

    @Given("a valid a comment id {int} user makes a Get request")
    public void aValidACommentIdUserMakesAGetRequest(int id) {
        response = RestResource.get(Constants.COMMENTS_ENDPOINT + "/"+id);
    }

    @And("the response body should contain the details of specific comment for id {int}")
    public void theResponseBodyShouldContainTheDetailsOfSpecificCommentForId(int id) {
        CommentsModule commentsModule = new CommentsModule();
        assertThat(response.getStatusCode(), equalTo(Status.SUCCESS_200));

        //verifying the response time of api
        assertThat(SpecBuilder.responseTimeValidation(response, properties.getProperty("response")), Matchers.is(true));
        commentsModule.verifySingleCommentDetails(response,id);
    }
}
