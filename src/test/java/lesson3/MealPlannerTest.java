package lesson3;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class MealPlannerTest {

    Properties properties = new Properties();

    long date = LocalDate.now().toEpochDay();

    String addId;
    String deletId;

    @Test
    void addDeleteMealTest() {
        try {
            addId = given()
                    .queryParam("hash", TestProperties.hash())
                    .queryParam("apiKey", TestProperties.apiKey())
                    .queryParam("username", TestProperties.username())
                    .body("{\n"
                            + " \"date\": " + date + ",\n"
                            + " \"slot\": 1,\n"
                            + " \"position\": 1,\n"
                            + " \"type\": \"RECIPE\",\n"
                            + " \"value\": {\n"
                            + " \"id\": 644953,\n"
                            + " \"servings\":2,\n"
                            + " \"title\": \"Goat Cheese Pesto Pizza\", \n"
                            + " \"imageType\": \"jpg\", \n "
                            + " }\n"
                            + "}")
                    .when()
                    .post("https://api.spoonacular.com/mealplanner/" + TestProperties.username() + "/items")
                    .then()
                    .statusCode(200)
                    .extract()
                    .jsonPath()
                    .get("id")
                    .toString();
        } finally {
            tearDownAdd();
        }
    }

    @Test
    void addDeleteToShoppingListTest() {

        try {
            deletId = given()
                    .queryParam("hash", TestProperties.hash())
                    .queryParam("apiKey", TestProperties.apiKey())
                    .queryParam("username", TestProperties.username())
                    .body("{\n"
                            + " \"item\": \"garlic\",\n"
                            + "\"parse\": true,\n "
                            + "}")
                    .when()
                    .post("https://api.spoonacular.com/mealplanner/" + TestProperties.username() + "/shopping-list/items")
                    .then()
                    .statusCode(200)
                    .extract()
                    .jsonPath()
                    .get("id")
                    .toString();

        } finally {
            tearDownDelete();
        }
    }


    void tearDownAdd() {
        given()
                .queryParam("hash", TestProperties.hash())
                .queryParam("apiKey", TestProperties.apiKey())
                .queryParam("username", TestProperties.username())
                .delete("https://api.spoonacular.com/mealplanner/" + TestProperties.username() + "/items/" + addId)
                .then()
                .statusCode(200);
    }

    void tearDownDelete() {
        given()
                .queryParam("hash", TestProperties.hash())
                .queryParam("username", TestProperties.username())
                .queryParam("apiKey", TestProperties.apiKey())
                .queryParam("id", deletId)
                .delete("https://api.spoonacular.com/mealplanner/" + TestProperties.username() + "/shopping-list/items/" + deletId)
                .then()
                .statusCode(200);
    }
}
