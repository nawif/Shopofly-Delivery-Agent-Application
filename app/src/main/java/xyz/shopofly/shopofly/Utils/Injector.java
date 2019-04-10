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

    public static Retrofit provideRetrofit(String baseUrl){

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.SECONDS)
                .readTimeout(1, TimeUnit.SECONDS)
                .writeTimeout(1, TimeUnit.SECONDS)
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }

    public static OrderService provideOrderService(){
        return provideRetrofit(Constants.BASE_URL).create(OrderService.class);
    }

    public static UserService provideUserService(){
        return provideRetrofit(Constants.BASE_URL).create(UserService.class);
    }

}
