package xyz.shopofly.shopofly.Utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.shopofly.shopofly.API.OrderService;

public class Injector {

    public static Retrofit provideRetrofit(String baseUrl){

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static OrderService provideOrderService(){
        return provideRetrofit(Constants.BASE_URL).create(OrderService.class);
    }
}
