package lesson6;

import com.github.javafaker.Faker;
import lesson6.api.ProductControllerApi;
import lesson6.db.dao.ProductsMapper;
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

import static org.hamcrest.MatcherAssert.assertThat;

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

        var session = DbConnectionUtils.createConnection();
        var productsMapper = session.getMapper(ProductsMapper.class);
        var createdDbProduct = productsMapper.selectByPrimaryKey(productDto.getId());

        assertThat(createdDbProduct.getTitle(), CoreMatchers.is(newProductDto.getTitle()));
        assertThat(createdDbProduct.getPrice(), CoreMatchers.is(newProductDto.getPrice()));

        session.close();
    }

    @SneakyThrows
    @AfterEach
    void tearDown() {
        DbConnectionUtils.deleteProductById(productDto.getId());
    }

}
