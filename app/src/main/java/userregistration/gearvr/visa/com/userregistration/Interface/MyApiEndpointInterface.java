package userregistration.gearvr.visa.com.userregistration.Interface;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import userregistration.gearvr.visa.com.userregistration.Model.User;

/**
 * Created by svuddara on 2/6/17.
 */

public interface MyApiEndpointInterface {

    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

//    @GET("users/{username}")
//    Call<User> getUser(@Path("username") String username);

    @POST("api/users")
    Call<User> createUser(@Body User user) ;
}
