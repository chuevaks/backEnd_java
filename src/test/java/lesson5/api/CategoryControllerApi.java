package lesson5.api;

import retrofit2.Call;
import retrofit2.http.*;
import lesson5.model.CategoryDto;

public interface CategoryControllerApi {
  /**
   * getCategoryById
   * 
   * @param id id (required)
   * @return Call&lt;CategoryDto&gt;
   */
  @GET("api/v1/categories/{id}")
  Call<CategoryDto> getCategoryByIdUsingGET(
    @retrofit2.http.Path("id") Long id
  );

}