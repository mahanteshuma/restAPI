import static io.restassured.RestAssured.*;

import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
public class RestAPITesting {

    //Get
    @Test
    public void getUser(){

        SoftAssert softAssert=new SoftAssert();
        Response response = get("https://reqres.in/api/users/2");

        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();
        softAssert.assertEquals(jsonPath.get("data.first_name"), "Janet");
        softAssert.assertEquals(jsonPath.get("data.last_name"), "Weaver");
        softAssert.assertEquals(response.getStatusCode(),StatusCode.sc_ok);
        softAssert.assertAll();
    }

    //Get
    @Test
    public void getUserCucumber(){
        baseURI = "https://reqres.in";
        given().header("Content-Type","application/json").contentType("application/json").
                accept("application/json").when().get("/api/users/2").then().statusCode(StatusCode.sc_ok).
                assertThat().body("data.first_name",equalTo("Janet")).body("data.last_name",equalTo("Weaver")).log().all();
    }

    //Post
    @Test
    public void postUserCucumber(){

//        Map<String,Object> map=new HashMap<>();
//        map.put("name","John");
//        map.put("age",24);
//        map.put("JobTitle","Test Engineer");
//        map.put("Salary",6.5);


        JSONObject jsonObject=new JSONObject();
        jsonObject.put("name","John");
        jsonObject.put("age",24);
        jsonObject.put("JobTitle","Test Engineer");
        jsonObject.put("Salary",6.5);

        baseURI = "https://reqres.in";
        given().header("Content-Type","application/json").contentType("application/json").
                accept("application/json").body(jsonObject.toJSONString()).when().post("/api/users").
                then().statusCode(StatusCode.sc_create).assertThat().body("name",equalTo("John")).body("JobTitle",equalTo("Test Engineer")).
                log().all();

    }

    //Patch
    @Test
    public void patchUserCucumber(){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("name","Push");
        jsonObject.put("age",24);
        jsonObject.put("JobTitle","Automation Engineer");
        jsonObject.put("Salary",6.5);

        baseURI = "https://reqres.in";
        given().header("Content-Type","application/json").contentType("application/json").
                accept("application/json").body(jsonObject.toJSONString()).when().patch("/api/users/2").
                then().statusCode(StatusCode.sc_ok).assertThat().body("name",equalTo("Push")).body("JobTitle",equalTo("Automation Engineer")).
                log().all();
    }

    //Put
    @Test
    public void putUserCucumber(){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("name","Pull");
        jsonObject.put("age",24);
        jsonObject.put("JobTitle","Automation Tester");
        jsonObject.put("Salary",6.5);

        baseURI = "https://reqres.in";
        given().header("Content-Type","application/json").contentType("application/json").
                accept("application/json").body(jsonObject.toJSONString()).when().put("/api/users/2").
                then().statusCode(StatusCode.sc_ok).assertThat().body("name",equalTo("Pull")).body("JobTitle",equalTo("Automation Tester")).
                log().all();
    }

    //Delete
    @Test
    public void deleteUserCucumber(){
        baseURI = "https://reqres.in";
        given().header("Content-Type","application/json").contentType("application/json").
                accept("application/json").when().delete("/api/users/2").
                then().statusCode(StatusCode.sc_accepted).log().all();
    }


}
