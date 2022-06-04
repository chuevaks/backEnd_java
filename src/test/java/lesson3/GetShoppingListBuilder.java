package lesson3;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lesson3.model.ShoppingList;

import static io.restassured.RestAssured.given;

public class GetShoppingListBuilder {

    private RequestSpecification _current;

    public GetShoppingListBuilder(){
        _current = given()
                .queryParam("hash", TestProperties.hash())
                .queryParam("username", TestProperties.username())
                .queryParam("apiKey", TestProperties.apiKey());
    }

    public Response get(){
        return _current
                .when()
                .get(TestProperties.baseUrl()+TestProperties.mealplanner()+TestProperties.username()+TestProperties.shoppingList());
    }

    public JsonPath getJson(){
        return get()
                .then()
                .statusCode(200)
                .extract()
                .jsonPath();
    }

    public ShoppingList getModel(){
        return get()
                .then()
                .extract()
                .response()
                .body()
                .as(ShoppingList.class);
    }
}

