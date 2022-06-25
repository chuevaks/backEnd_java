package lesson6;

import com.github.javafaker.Faker;
import lesson6.api.ProductControllerApi;
import lesson6.model.ProductDto;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;


public class GetProductListTest {

    static ProductControllerApi productControllerApi;
    ProductDto productDto1;
    ProductDto productDto2;
    Faker faker = new Faker();

    @BeforeAll
    static void beforeAll() {
        productControllerApi = RetrofitUtils.getRetrofit()
                .create(ProductControllerApi.class);
    }
    @BeforeEach
    void setUp() throws IOException {
        productDto1 = new ProductDto()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random()*10000));
        productDto2 = new ProductDto()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random()*10000));

        Response<ProductDto> response = productControllerApi.createNewProductUsingPOST(productDto1)
                .execute();
        productDto1 = productDto1.withId(response.body().getId());

        response = productControllerApi.createNewProductUsingPOST(productDto2)
                .execute();
        productDto2 = productDto2.withId(response.body().getId());
    }

    @Test
    void setProductList() throws IOException {

        Response<List<ProductDto>> response = productControllerApi.getProductsUsingGET().execute();
        boolean hasProduct1 = response.body().contains(productDto2);
        assertThat(hasProduct1, CoreMatchers.is(true));
        boolean hasProduct2 = response.body().contains(productDto2);
        assertThat(hasProduct2, CoreMatchers.is(true));
    }

    @SneakyThrows
    @AfterEach
    void tearDown() {
        DbConnectionUtils.deleteProductById(productDto1.getId());
        DbConnectionUtils.deleteProductById(productDto2.getId());
    }
}
