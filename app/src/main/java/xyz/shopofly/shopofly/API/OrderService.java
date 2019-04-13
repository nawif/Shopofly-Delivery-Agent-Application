package xyz.shopofly.shopofly.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import xyz.shopofly.shopofly.Model.Network.Order;
import xyz.shopofly.shopofly.Model.Network.Payment;

public interface OrderService {

    @GET("store/orders/{id}")
    Call<Order> getOrder(@Path("id") int id);


    @GET("agent/orders")
    Call<List<Order>> getOrderList(@Header("Authorization") String authorization);

    @POST("store/processPayment")
    Call<Payment> processPayment(@Header("Authorization") String authorization, @Body Payment payment);

    @GET("agent/billstatus/{id}")
    Call<Void> paymentStatus(@Header("Authorization") String authorization, @Path("id") int id);

}
