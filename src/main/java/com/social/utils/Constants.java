package com.social.utils;

public interface Constants {


     String BASE_URI = "https://"+System.getProperty("env")+".typicode.com";

    String QA_BASE_URI = "https://jsonplaceholder.typicode.com";

    String STAGE_BASE_URI = "https://jsonplaceholder.typicode.com";
    
    String COMMENTS_ENDPOINT = "/comments";

    String POST_ENDPOINT = "/posts";

    String USERS_ENDPOINT = "/users";
}
