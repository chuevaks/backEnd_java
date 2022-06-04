package lesson3;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {


    private static Properties prop = new Properties();

    static {
        try {
            prop.load(new FileInputStream("src/main/resources/lesson3.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String apiKey() {
        return prop.getProperty("apiKey");
    }

    public static String baseUrl() {
        return prop.getProperty("url");
    }

    public static String complexSearch() {
        return prop.getProperty("complexSearch");
    }

    public static String classifyCuisine() {
        return prop.getProperty("classifyCuisine");
    }

    public static String hash() {
        return prop.getProperty("hash");
    }

    public static String username() {
        return prop.getProperty("username");
    }

    public static  String shoppingList(){
        return prop.getProperty("shoppingList");
    }

    public static  String mealplanner(){
        return prop.getProperty("mealplanner");
    }

    public static  String items(){
        return prop.getProperty("items");
    }


}