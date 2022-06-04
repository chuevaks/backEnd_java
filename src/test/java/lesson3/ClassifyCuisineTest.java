package lesson3;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ClassifyCuisineTest {



    @Test
    void postClassifyCuisine() {
        String cuisine = new PostClassifyCuisineBuilder()
                .title("Garlic Butter Shrimp with Asparagus")
                .ingredientList("5 clove garlic")
                .postJson();

        assertThat(cuisine, equalTo("Mediterranean"));
    }

    @Test
    void postClassifyCuisine2() {
        String cuisine = new PostClassifyCuisineBuilder()
                .title("Chinese Vegetable Stir-Fry")
                .ingredientList("sesame oil")
                .postJson();

        assertThat(cuisine, equalTo("Chinese"));
    }

    @Test
    void postClassifyCuisine3() {
        String cuisine = new PostClassifyCuisineBuilder()
                .title("Thai Noodle Salad")
                .ingredientList("sesame oil,1 red bell pepper,3 scallions")
                .postJson();

        assertThat(cuisine, equalTo("Asian"));
    }

    @Test
    void postClassifyCuisine4() {
        String cuisine = new PostClassifyCuisineBuilder()
                .title("Pea soup with parmesan and a just-boiled egg")
                .ingredientList("peas,edd,parmesan")
                .postJson();

        assertThat(cuisine, equalTo("Mediterranean"));
    }

    @Test
    void postClassifyCuisine5() {
        String cuisine = new PostClassifyCuisineBuilder()
                .title("Bibimbap")
                .ingredientList("rice,bean sprouts,gochujang")
                .postJson();

        assertThat(cuisine, equalTo("Korean"));
    }
}
