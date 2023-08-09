package com.social.steps;

import com.social.modules.PostModule;
import com.social.pojo.MainPost;
import com.social.resource.RestResource;
import com.social.resource.SpecBuilder;
import com.social.utils.Constants;
import com.social.utils.FileLocations;
import com.social.utils.PropertyInit;
import com.social.utils.Status;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

import java.util.LinkedHashMap;
import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;

public class PostSteps {
    
   private Response response = null;
    Properties properties = PropertyInit.propertyLoader(FileLocations.DATA_DIRECTORY);

    @Given("using valid user account Id user makes a post in social network")
    public void usingValidUserAccountIdUserMakesAPostInSocialNetwork() {
        PostModule postModule = new PostModule();
        LinkedHashMap<String, Object> postContent = postModule.postContentUsingMap();
        response = RestResource.post(postContent, Constants.POST_ENDPOINT);
    }

    @Then("validating the post is successfully published with the expected content")
    public void validatingThePostIsSuccessfullyPublishedWithTheExpectedContent() {
        assertThat(response.getStatusCode(),equalTo(Status.CREATED_201));

        //verifying the response time of api
        assertThat(SpecBuilder.responseTimeValidation(response, properties.getProperty("response")), Matchers.is(true));
        assertThat(response.path("userId"),Matchers.lessThan(100));
        assertThat(response.path("title"), Matchers.not(Matchers.emptyString()));
        assertThat(response.path("body"),Matchers.not(Matchers.emptyString()));
        assertThat(response.path("id"),Matchers.is(notNullValue()));
    }

    @Given("retrieve all posts from social Network using Get call")
    public void retrieveAllPostsFromSocialNetworkUsingGetCall() {
      response = RestResource.get(Constants.POST_ENDPOINT);

    }

    @Then("verify user able to fetch all posts and validate successfully")
    public void verifyUserAbleToFetchAllPostsAndValidateSuccessfully() {
        PostModule postModule = new PostModule();
        assertThat(response.getStatusCode(),equalTo(Status.SUCCESS_200));
        postModule.verifyGetAllPost(response);
    }

    @Given("retrieve single posts from social Network for {int}")
    public void retrieveSinglePostsFromSocialNetworkFor(int id) {
        response = RestResource.get(Constants.POST_ENDPOINT+"/"+id);
    }

    @Then("verify user able to fetch single post {int} and validate successfully")
    public void verifyUserAbleToFetchSinglePostAndValidateSuccessfully(int id) {
        //Deserialize concept using pojo classes
       MainPost singlePostResponse = response.as(MainPost.class);
        assertThat(response.getStatusCode(),equalTo(Status.SUCCESS_200));
        assertThat(singlePostResponse.getUserId(), is(notNullValue()));
        assertThat(singlePostResponse.getTitle(),is(notNullValue()));
        assertThat(singlePostResponse.getId(), equalTo(id));
    }
}
