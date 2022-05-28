package lesson3;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ComplexSearchTest {

    @BeforeAll
    static void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void getRecipeComplexSearchTest() {
        JsonPath response = new GetRecipeComplexSearchBuilder()
                .cuisine("Japanese")
                .query("soup")
                .getJson();

        assertThat(response.get("totalResults"), equalTo(320));
    }

    @Test
    void getRecipeComplexSearchTest2() {
        JsonPath response = new GetRecipeComplexSearchBuilder()
                .diet("vegetarian")
                .type("salad")
                .includeIngredients("tomato")
                .getJson();

        assertThat(response.get("totalResults"), equalTo(44));
    }

    @Test
    void getRecipeComplexSearchTest3() {
        JsonPath response = new GetRecipeComplexSearchBuilder()
                .cuisine("italian")
                .query("pizza")
                .includeIngredients("cheese")
                .getJson();

        assertThat(response.get("totalResults"), equalTo(12));
    }

    @Test
    void getRecipeComplexSearchTest4() {
        JsonPath response = new GetRecipeComplexSearchBuilder()
                .maxSugar(30)
                .query("pizza")
                .includeIngredients("pesto")
                .getJson();

        assertThat(response.get("totalResults"), equalTo(2));
    }

    @Test
    void getRecipeComplexSearchTest5() {
        JsonPath response = new GetRecipeComplexSearchBuilder()
                .type("dessert")
                .includeIngredients("chocolate")
                .intolerances("dairy")
                .diet("vegetarian")
                .getJson();

        assertThat(response.get("totalResults"), equalTo(6));
    }


}

