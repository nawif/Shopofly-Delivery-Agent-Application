package xyz.shopofly.shopofly.API;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import xyz.shopofly.shopofly.Model.Network.Login;
import xyz.shopofly.shopofly.Model.Network.User;

public interface UserService {

    @POST("auth/login")
    Call<User> login(@Body Login login);


}
