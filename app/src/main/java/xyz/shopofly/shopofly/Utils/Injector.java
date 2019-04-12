package xyz.shopofly.shopofly.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.shopofly.shopofly.API.OrderService;
import xyz.shopofly.shopofly.API.UserService;

public class Injector {

    private static Retrofit provideRetrofit(){

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }

    public static OrderService provideOrderService(){
        return provideRetrofit().create(OrderService.class);
    }

    public static UserService provideUserService(){
        return provideRetrofit().create(UserService.class);
    }



}
