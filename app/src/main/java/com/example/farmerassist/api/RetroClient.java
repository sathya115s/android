package com.example.farmerassist.api;

import com.example.farmerassist.utils.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {

    private static Retrofit retrofit = null;

    private static Retrofit getClient() {
        if (retrofit == null) {

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .callTimeout(1, TimeUnit.MINUTES)
                    .addInterceptor(loggingInterceptor)  // Add the logging interceptor
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(okHttpClient)  // Set the OkHttpClient with interceptors
                    .addConverterFactory(GsonConverterFactory.create())  // Convert JSON responses
                    .build();

        }

        return retrofit;
    }


    public static Interface getInterface(){

        return getClient().create(Interface.class);
    }
}
