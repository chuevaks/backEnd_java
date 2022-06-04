package lesson3;


import lesson3.model.ShoppingList;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MealPlannerTest {

    long date = LocalDate.now().toEpochDay();

    @Test
    void addDeleteMealTest() {
        int addId = 0;

        try {
            addId = new PostToMealPlanBuilder()
                    .date(date)
                    .slot(1)
                    .position(1)
                    .type("RECIPE")
                    .value(644953, 2, "Goat Cheese Pesto Pizza", "jpg")
                    .getId();
        } finally {
            deleteRecipeFromMealPlanner(addId);
        }
    }

    @Test
    void addDeleteToShoppingListTest() {

        int deletId = 0;
        try {
            deletId = new AddToShoppingListBuilder()
                    .item("garlic")
                    .parse(true)
                    .getId();

            ShoppingList response = new GetShoppingListBuilder()
                    .getModel();

            assertThat(hasId(response, deletId), equalTo(true));


        } finally {
            deleteItemFromShoppingList(deletId);
        }
    }

    boolean hasId(ShoppingList shoppingList, int id){
        for (var aisle: shoppingList.getAisles()) {
            for (var item: aisle.getItems()) {
                if (item.getId() == id)
                    return true;
            }
        }

        return false;
    }

    void deleteRecipeFromMealPlanner(int recipeId) {
        given()
                .queryParam("hash", TestProperties.hash())
                .queryParam("apiKey", TestProperties.apiKey())
                .queryParam("username", TestProperties.username())
                .delete(TestProperties.baseUrl() + TestProperties.mealplanner() + TestProperties.username() +TestProperties.items()+ "/" + recipeId)
                .then()
                .statusCode(200);
    }

    void deleteItemFromShoppingList(int itemId) {
        given()
                .queryParam("hash", TestProperties.hash())
                .queryParam("username", TestProperties.username())
                .queryParam("apiKey", TestProperties.apiKey())
                .queryParam("id", itemId)
                .delete(TestProperties.baseUrl() + TestProperties.mealplanner() + TestProperties.username() + TestProperties.shoppingList() + TestProperties.items()+ "/" + itemId)
                .then()
                .statusCode(200);
    }
}
