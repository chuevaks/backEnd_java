package lesson3;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class PostClassifyCuisineBuilder {

    private RequestSpecification _current;

    public PostClassifyCuisineBuilder() {
        _current = given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .queryParam("apiKey", TestProperties.apiKey());
    }

    private void modifyBody(String key, String value){
        _current = _current.formParam(key, value);
    }

    public PostClassifyCuisineBuilder title(String title){
        modifyBody("title", title);
        return this;
    }

    public PostClassifyCuisineBuilder ingredientList(String ingredientList){
        modifyBody("ingredientList", ingredientList);
        return this;
    }

    public Response post(){
        return _current
                .when()
                .post(TestProperties.baseUrl()+TestProperties.classifyCuisine());
    }

    public String postJson(){
        return post()
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("cuisine")
                .toString();
    }

}
