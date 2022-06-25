package lesson3;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lesson3.model.AddToMealPlanRequest;
import lesson3.model.AddToMealPlanValue;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;

public class PostToMealPlanBuilder {

    private RequestSpecification _current;
    private AddToMealPlanRequest _request;

    public PostToMealPlanBuilder() {
        _request = new AddToMealPlanRequest();

        _current = given()
                .queryParam("hash", TestProperties.hash())
                .queryParam("username", TestProperties.username())
                .queryParam("apiKey", TestProperties.apiKey());
    }

    public Response post() {
        return _current
                .body(_request)
                .when()
                .post(TestProperties.baseUrl() + TestProperties.mealplanner() + TestProperties.username() + TestProperties.items());
    }

    public int getId() {
        return post()
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getInt("id");
    }

    public PostToMealPlanBuilder date(Long value){
        _request.setDate(value);

        return this;
    }

    public PostToMealPlanBuilder slot(int value){
        _request.setSlot(value);

        return this;
    }

    public PostToMealPlanBuilder position(int value){
        _request.setPosition(value);

        return this;
    }

    public PostToMealPlanBuilder type(String value){
        _request.setType(value);

        return this;
    }

    public PostToMealPlanBuilder value(int id, int servings, String title, String imageType ){
        var value = new AddToMealPlanValue();
        value.setId(id);
        value.setServings(servings);
        value.setTitle(title);
        value.setImageType(imageType);
        _request.setValue(value);

        return this;
    }

}
