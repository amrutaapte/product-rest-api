package com.product.main;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.when;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RestAssuredRequests {

	
    private static String requestBody = "{" +
            "  \"name\": \"Test Product\"," +
            "  \"description\": \"Test Description\"," +
            "  \"brand\": \"Test Brand\"," +
            "  \"tags\": [\"Test Tag 1\", \"Test Tag 2\"]," +
            "  \"category\": \"Test Category\"}";
    
    private static String category = "Test Category"; 
    

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080";
    }
    
    
    @Test
    @Order(1)
    public void product_post_returns_valid_product() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/v1/products")
                .then()
                .extract().response();
                

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Adding new product", response.header("Message-from-API"));
        Assertions.assertEquals("Test Product", response.jsonPath().getString("name"));
        Assertions.assertEquals("Test Category", response.jsonPath().getString("category"));

    }
    

    @Test
    @Order(2)
    public void product_getByCategory_returns_valid_page_json() {
    	
        Response getResponse = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/v1/products/{category}", category)
                .then()
                .extract().response();

        Assertions.assertEquals(200, getResponse.statusCode());
        Assertions.assertEquals("1", getResponse.jsonPath().getString("totalPages"));
    }
    
 
}
