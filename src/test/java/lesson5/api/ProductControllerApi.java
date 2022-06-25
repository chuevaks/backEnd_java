package lesson5.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;
import lesson5.model.ProductDto;
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
    @retrofit2.http.Body ProductDto p
  );

  /**
   * Delete product
   * 
   * @param id Id of the product (required)
   * @return Call&lt;Void&gt;
   */
  @DELETE("api/v1/products/{id}")
  Call<ResponseBody> deleteByIdUsingDELETE(
    @retrofit2.http.Path("id") Long id
  );

  /**
   * Returns a specific product by their identifier. 404 if does not exist.
   * 
   * @param id Id of the book to be obtained. Cannot be empty. (required)
   * @return Call&lt;ProductDto&gt;
   */
  @GET("api/v1/products/{id}")
  Call<ProductDto> getProductByIdUsingGET(
    @retrofit2.http.Path("id") Long id
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
    @retrofit2.http.Body ProductDto p
  );

}