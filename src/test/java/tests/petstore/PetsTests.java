package tests.petstore;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import io.restassured.http.ContentType;
import models.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;
import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PetsTests extends BasePetTest {
    private Integer createdPet;

    @Test
    public void findByAvailableStatusTest(){
       List<Pet> pets = given().contentType(ContentType.JSON)
               .when().get(BASE_URL+"/pet/findByStatus?status=available")
               .then().assertThat().body(matchesJsonSchemaInClasspath("schemas/findByAvailableStatusTest.json"))
               .extract().jsonPath().getList("", Pet.class);
        softAssertions.assertThat(pets).allMatch(e->e.getStatus().equals("available"));
        softAssertions.assertAll();
    }

    @Test
    public void createPetTest(){
        var body = "{\n" +
                "  \"id\": 0,\n" +
                "  \"category\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"dogs\"\n" +
                "  },\n" +
                "  \"name\": \"Buddy\",\n" +
                "  \"photoUrls\": [\"https://example.com/buddy.jpg\"],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"friendly\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

            var response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when().post(BASE_URL+"/pet")
                    .then().statusCode(200)
                    .extract().jsonPath();

        softAssertions.assertThat(response.get("name").toString()).isEqualTo("Buddy");
        softAssertions.assertThat(response.get("status").toString()).isEqualTo("available");
        softAssertions.assertAll();
        createdPet = response.getInt("id");
    }
    @Test
    public void getPetInventoryByStatusToMap(){
        Map<String,Integer> inventoryMap = given()
                .contentType(ContentType.JSON)
                .when().get(BASE_URL+"/store/inventory").then()
                .extract().body().jsonPath().getMap("");
    }


}
