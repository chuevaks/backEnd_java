package lesson6.api;

import lesson6.model.CategoryDto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CategoryControllerApi {
  /**
   * getCategoryById
   * 
   * @param id id (required)
   * @return Call&lt;CategoryDto&gt;
   */
  @GET("api/v1/categories/{id}")
  Call<CategoryDto> getCategoryByIdUsingGET(
    @Path("id") Long id
  );

}