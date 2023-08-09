package com.social.steps;

import com.social.modules.UserModule;
import com.social.resource.RestResource;
import com.social.resource.SpecBuilder;
import com.social.utils.Constants;
import com.social.utils.FileLocations;
import com.social.utils.PropertyInit;
import com.social.utils.Status;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UserSteps {

    private Response response = null;
    Properties properties = PropertyInit.propertyLoader(FileLocations.DATA_DIRECTORY);

    @When("the user requests to get all users")
    public void theUserRequestsToGetAllUsers() {
        response = RestResource.get(Constants.USERS_ENDPOINT);
    }

    @Then("the list of users should be retrieved successfully")
    public void theListOfUsersShouldBeRetrievedSuccessfully() {
        //Deserialize  concept using pojo
        UserModule userModule = new UserModule();
        userModule.verifyAllUserList(response);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int status) {
        assertThat(response.getStatusCode(), equalTo(status));
    }

    @Given("a valid user ID {int} and make a get request")
    public void aValidUserIDAndMakeAGetRequest(int id) {
        response = RestResource.get(Constants.USERS_ENDPOINT+"/"+id);
    }

    @And("the response body should contain the details of the user with ID {int}")
    public void theResponseBodyShouldContainTheDetailsOfTheUserWithID(int id) {
        //Deserialize  concept using pojo
        UserModule userModule = new UserModule();
        userModule.verifySingleUserDetails(response,id);
    }


    @Given("an invalid user ID {int} and make a get request")
    public void anInvalidUserIDAndMakeAGetRequest(int invalidUserId) {
        response = RestResource.get(Constants.USERS_ENDPOINT+"/id");
    }

    @Then("the response should not found with correct status code")
    public void theResponseShouldNotFoundWithCorrectStatusCode() {
        assertThat(response.getStatusCode(), equalTo(Status.NOT_FOUND_ERROR));
    }

    @Then("verify response status code should be {int} and response time")
    public void verifyResponseStatusCodeShouldBeAndResponseTime(int statusCode) {
        assertThat(response.getStatusCode(), equalTo(statusCode));
        //verifying the response time of api
        assertThat(SpecBuilder.responseTimeValidation(response, properties.getProperty("response")), Matchers.is(true));
    }
}
