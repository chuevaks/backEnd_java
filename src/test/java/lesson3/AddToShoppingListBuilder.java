package lesson3;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lesson3.model.AddToMealPlanRequest;
import lesson3.model.AddToMealPlanValue;
import lesson3.model.AddToShoppingListRequest;

import static io.restassured.RestAssured.given;

public class AddToShoppingListBuilder {
    private RequestSpecification _current;
    private AddToShoppingListRequest _request;

    public AddToShoppingListBuilder() {
        _request = new AddToShoppingListRequest();

        _current = given()
                .queryParam("hash", TestProperties.hash())
                .queryParam("username", TestProperties.username())
                .queryParam("apiKey", TestProperties.apiKey());
    }

    public Response post() {
        return _current
                .body(_request)
                .when()
                .post(TestProperties.baseUrl() + TestProperties.mealplanner() + TestProperties.username() + TestProperties.shoppingList() + TestProperties.items());
    }

    public int getId() {
        return post()
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getInt("id");
    }



    public AddToShoppingListBuilder item(String value){
        _request.setItem(value);

        return this;
    }

    public AddToShoppingListBuilder parse(Boolean value){
        _request.setParse(value);

        return this;
    }


}
