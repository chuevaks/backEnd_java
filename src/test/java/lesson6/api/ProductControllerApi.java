package lesson6.api;

import lesson6.model.ProductDto;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ProductControllerApi {
  /**
   * Creates a new product. If id !&#x3D; null, then throw bad request response
   * 
   * @param p p (required)
   * @return Call&lt;Object&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @POST("api/v1/products")
  Call<ProductDto> createNewProductUsingPOST(
    @Body ProductDto p
  );

  /**
   * Delete product
   * 
   * @param id Id of the product (required)
   * @return Call&lt;Void&gt;
   */
  @DELETE("api/v1/products/{id}")
  Call<ResponseBody> deleteByIdUsingDELETE(
    @Path("id") Long id
  );

  /**
   * Returns a specific product by their identifier. 404 if does not exist.
   * 
   * @param id Id of the book to be obtained. Cannot be empty. (required)
   * @return Call&lt;ProductDto&gt;
   */
  @GET("api/v1/products/{id}")
  Call<ProductDto> getProductByIdUsingGET(
    @Path("id") Long id
  );

  /**
   * Returns products
   * 
   * @return Call&lt;List&lt;ProductDto&gt;&gt;
   */
  @GET("api/v1/products")
  Call<List<ProductDto>> getProductsUsingGET();
    

  /**
   * Modify product
   * 
   * @param p p (required)
   * @return Call&lt;Object&gt;
   */
  @Headers({
    "Content-Type:application/json"
  })
  @PUT("api/v1/products")
  Call<ResponseBody> modifyProductUsingPUT(
    @Body ProductDto p
  );

}