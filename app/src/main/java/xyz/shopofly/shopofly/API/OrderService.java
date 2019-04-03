package xyz.shopofly.shopofly.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import xyz.shopofly.shopofly.Model.Network.Order;

public interface OrderService {

    @GET("store/orders/{id}")
    Call<Order> getOrder(@Path("id") int id);

}
