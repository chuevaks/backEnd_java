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
public class ModifyProductTest {
    static ProductControllerApi productControllerApi;
    ProductDto productDto;

    Faker faker = new Faker();


    @BeforeAll
    static void beforeAll() {
        productControllerApi = RetrofitUtils.getRetrofit()
                .create(ProductControllerApi.class);
    }

    @BeforeEach
    void setUp() throws IOException {
        productDto = new ProductDto()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random()*10000));
        Response<ProductDto> response = productControllerApi.createNewProductUsingPOST(productDto)
                .execute();

        productDto = productDto.withId(response.body().getId());
    }

    @Test
    void modifyProductTest() throws IOException{
        ProductDto newProductDto = productDto.withPrice(100);
        Response<ResponseBody> response = productControllerApi.modifyProductUsingPUT(newProductDto).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));

       Response<ProductDto> updatedResponse = productControllerApi.getProductByIdUsingGET(productDto.getId()).execute();
       assertThat(newProductDto.equals(updatedResponse.body()), CoreMatchers.is(true));
    }

    @SneakyThrows
    @AfterEach
    void tearDown() {
        Response<ResponseBody> response = productControllerApi.deleteByIdUsingDELETE(productDto.getId()).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

}
