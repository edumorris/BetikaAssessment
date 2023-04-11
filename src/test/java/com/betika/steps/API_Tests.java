package com.betika.steps;

import org.json.JSONArray;
import org.json.JSONObject;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpUriRequest;
//import org.apache.http.impl.client.HttpClientBuilder;

//import java.io.IOException;

import static com.betika.utility.ConfigReader.confRead;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.reset;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class API_Tests {
    Response response;

    boolean userExists = false;
//     HttpResponse httpResponse;
    @Given("a request is made to the endpoint")
    public void aRequestIsMadeToTheEndpoint() {
        RestAssured.baseURI = confRead("api_base_uri");
        RestAssured.basePath = confRead("api_base_path");

        RequestSpecification httpRequest = given();

        response = httpRequest.get("Users");
//        HttpUriRequest request = new HttpGet(confRead("api_base_uri") + confRead("api_base_path") + "Users");
//
//        httpResponse = HttpClientBuilder.create().build().execute(request);
        reset();
    }

    @Then("a {int} response should be received")
    public void aResponseShouldBeReceived(int responseCode) {
//            assertThat(httpResponse.getStatusLine().getStatusCode(), is(responseCode));
            assertThat(response.statusCode(), is(responseCode));
    }

    @And("the {string} should exist")
    public void theShouldExist(String user) {
//        System.out.println(httpResponse.getEntity());
        String jsonString = response.body().asString();
        JSONArray jsonArray = new JSONArray(jsonString);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            String userName = jsonObject.getString("userName");

            userExists = userName.equals(user);

            if (userExists)
                break;
        }

        System.out.println("Number of objects in the response = " + jsonArray.length()); // Number of responses received
        assertThat(userExists, is(true));
    }
}
