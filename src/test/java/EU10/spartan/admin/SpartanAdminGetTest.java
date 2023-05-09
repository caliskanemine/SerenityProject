package EU10.spartan.admin;


import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@SerenityTest
public class SpartanAdminGetTest {

    @BeforeAll
    public static void init(){

       baseURI ="https://44.195.19.167:7000";

    }

    @Test
    public void getAllSpartan(){


        given().accept(ContentType.JSON).
                and().auth().basic("admin", "admin").
                when().get("/api/spartans").
                then().statusCode(200).
                and().contentType(ContentType.JSON);

    }



}
