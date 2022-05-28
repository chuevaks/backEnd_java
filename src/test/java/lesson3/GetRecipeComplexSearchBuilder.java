package lesson3;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class GetRecipeComplexSearchBuilder {

    private RequestSpecification _current;

    public GetRecipeComplexSearchBuilder() {
        _current = given()
                .queryParam("apiKey", TestProperties.apiKey());
    }

    public GetRecipeComplexSearchBuilder cuisine(String cuisine){
        _current = _current.queryParam("cuisine", cuisine);
        return this;
    }

    public GetRecipeComplexSearchBuilder query(String query){
        _current = _current.queryParam("query", query);
        return this;
    }

    public GetRecipeComplexSearchBuilder includeIngredients(String includeIngredients){
        _current = _current.queryParam("includeIngredients", includeIngredients);
        return this;
    }


    public GetRecipeComplexSearchBuilder type(String type){
        _current = _current.queryParam("type", type);
        return this;
    }

    public GetRecipeComplexSearchBuilder diet(String diet){
        _current = _current.queryParam("diet", diet);
        return this;
    }


    public GetRecipeComplexSearchBuilder intolerances(String intolerances){
        _current = _current.queryParam("intolerances", intolerances);
        return this;
    }

    public GetRecipeComplexSearchBuilder maxCalories(int maxCalories){
        _current = _current.queryParam("maxCalories", maxCalories);
        return this;
    }

    public GetRecipeComplexSearchBuilder maxSugar(int maxSugar){
        _current = _current.queryParam("maxSugar", maxSugar);
        return this;
    }


    public Response get(){
        return _current
                .when()
                .get(TestProperties.baseUrl()+TestProperties.complexSearch());
    }


    public JsonPath getJson(){
        return get()
                .body()
                .jsonPath();
    }
}
