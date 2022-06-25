package lesson5;

import com.github.javafaker.Faker;
import lesson5.api.ProductControllerApi;
import lesson5.model.ProductDto;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;

@Disabled("Disabled!")
public class CreateProductTest {

    static ProductControllerApi productControllerApi;
    ProductDto productDto;
    Faker faker = new Faker();
    Long id;

    @BeforeAll
    static void beforeAll() {
        productControllerApi = RetrofitUtils.getRetrofit()
                .create(ProductControllerApi.class);
    }

    @BeforeEach
    void setUp() {
        productDto = new ProductDto()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random()*10000));
    }

    @Test
    void createProductInFoodCategoryTest() throws IOException {
        Response<ProductDto> response = productControllerApi.createNewProductUsingPOST(productDto)
                .execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        id =  response.body().getId();

    }


    @SneakyThrows
    @AfterEach
    void tearDown() {
        Response<ResponseBody> response = productControllerApi.deleteByIdUsingDELETE(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

}
