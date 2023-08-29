package com.social.resource;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import io.qameta.allure.restassured.AllureRestAssured;

import java.util.concurrent.TimeUnit;

import static com.social.utils.Constants.BASE_URI;

public class SpecBuilder {


    private static Logger logger = LogManager.getLogger(SpecBuilder.class);

    /**
     * This method is used to create/setting up a request with different parameter
     *
     * @return : Request is returned
     * @author : Mohammed Haseeb
     */
    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL)
                .build();
    }

    /**
     * This method is used to extract response and performed common assertion applicable for the majority of responses
     *
     * @return : Response in Json format
     * @author : Mohammed Haseeb
     */
    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();

    }


    /**
     * This method is used to capture response time of API and perform validation
     *
     * @param response     : actual response
     * @param responseTime : long ex: 12
     * @return : boolean true or false
     * @author : Mohammed Haseeb
     */
   public static boolean responseTimeValidation(Response response, String responseTime) {
        try {
            long expectedResponseInMillSec = TimeUnit.SECONDS.toMillis(Long.parseLong(responseTime));

            ValidatableResponse actualResponse = response.then();
            long actualResponseInMillSec = actualResponse.extract().timeIn(TimeUnit.MILLISECONDS);
            if (actualResponseInMillSec < expectedResponseInMillSec) {
                logger.info("Response time in milliseconds: " + actualResponseInMillSec);
                return true;
            } else {
                logger.info("actual Response time in milli seconds : " + actualResponseInMillSec +
                        "expected response time in milli seconds :" + expectedResponseInMillSec);
                return false;
            }
        } catch (NumberFormatException ex) {
            logger.error("Invalid response time format: " + responseTime, ex);
            return false;
        } catch (Exception ex) {
            logger.error("An error occurred during response time validation", ex);
            return false;
        }
    }


}
