package EU10.spartan.admin;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.*;

import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SerenityTest
public class SpartanAdminGetTest {

    @BeforeAll
    public static void init(){
        //baseURI

       baseURI ="http://44.197.123.56:8000";

    }

    @Test
    public void getAllSpartan(){

                given().accept(ContentType.JSON).
                and().auth().basic("admin", "admin").
                when().get("/api/spartans").
                then().statusCode(200).
                and().contentType(ContentType.JSON).
                extract().response();
    }

    /*
      <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.0.0-M5</version>
                <configuration>
                    <testFailureIgnore>true</testFailureIgnore>
                </configuration>
            </plugin>
     */


    @Test
    public void getOneSpartan(){

       given().accept(ContentType.JSON).
                and().auth().basic("admin", "admin").
                pathParam("id", 15).
                when().get("/api/spartans/{id}").
                then().statusCode(200).
                and().contentType(ContentType.JSON).
                extract().response();

       //if you send a request using SerenityRest, the response object
        //can be obtained from the method called lastResponse() without being saved seperately
        //same with Response response object
        System.out.println("Status Code = " + lastResponse().statusCode());

        //print id
        //instead of response.path, we use lastResponse.path()
        System.out.println("lastResponse().path(\"id\") = " + lastResponse().path("id"));

        //use jsonpath with lastResponse and get the name
        String name = lastResponse().jsonPath().getString("name");
        System.out.println("name = " + name);

    }

    @DisplayName("GET request with Serenity Assertion way")
    @Test
    public void getOneSpartanAssertion(){

        given()
                .accept(ContentType.JSON)
                .and()
                .auth().basic("admin","admin")
                .pathParam("id",15)
                .when()
                .get("/api/spartans/{id}");

        //serenity way of assertion
        Ensure.that("Status code is 200", validatableResponse -> validatableResponse.statusCode(200));

        Ensure.that("Content-type is JSON", vRes -> vRes.contentType(ContentType.JSON) );

        Ensure.that("Id is 15", vRes -> vRes.body("id", Matchers.is(15)));

    }



}
