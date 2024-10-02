package com.example.farmerassist.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.farmerassist.api.Request;
import com.example.farmerassist.api.RetroClient;
import com.example.farmerassist.responses.CommonResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public interface Constants {

    String BASE_URL = "https://8d71-2405-201-e040-8063-3c54-aea-8044-8a71.ngrok-free.app";

     static SharedPreferences getSF(Context context) {
        return context.getSharedPreferences(Constants.SF_NAME, Context.MODE_PRIVATE);
     }

    String USER_ID = "user_id";
    String SF_NAME = "my_app_prefs";
    String USER_TYPE = "user_type";
    String USER_NAME = "user_name";
    String USER_EMAIL = "user_email";

    default void apiCall(String userName, String password) {
        Context context=null;
        Request.LoginRequest loginRequest = new Request.LoginRequest(userName, password);
       Call<CommonResponse> responseCall = RetroClient.getInterface().sample();
       responseCall.enqueue(new Callback<CommonResponse>() {
           @Override
           public void onResponse(@NonNull Call<CommonResponse> call, @NonNull Response<CommonResponse> response) {
               if(response.isSuccessful()) {
                   if(response.body().isSuccess()) {
                       Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                   }else{
                       Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                   }
               }
           }

           @Override
           public void onFailure(@NonNull Call<CommonResponse> call, @NonNull Throwable t) {
               Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
               Log.e("TAG", "onFailure: " + t.getMessage());
           }
       });

    }



}
