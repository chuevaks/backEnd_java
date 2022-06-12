package lesson6;

import com.github.javafaker.Faker;
import lesson6.api.ProductControllerApi;
import lesson6.db.dao.ProductsMapper;
import lesson6.model.ProductDto;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteProductTest {
    static ProductControllerApi productControllerApi;
    ProductDto productDto;
    Faker faker = new Faker();

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
        Long id =  response.body().getId();
        Response<ResponseBody> deleteResponse = productControllerApi.deleteByIdUsingDELETE(id).execute();
        assertThat(deleteResponse.isSuccessful(), CoreMatchers.is(true));

        var session = DbConnectionUtils.createConnection();
        var productsMapper = session.getMapper(ProductsMapper.class);
        var createdDbProduct = productsMapper.selectByPrimaryKey(id);

        assertThat(createdDbProduct, CoreMatchers.nullValue());

        session.close();
    }


}
