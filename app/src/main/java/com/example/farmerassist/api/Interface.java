package com.example.farmerassist.api;

import com.example.farmerassist.responses.AddCropResponse;
import com.example.farmerassist.responses.AddIncomeResponse;
import com.example.farmerassist.responses.CommonResponse;
import com.example.farmerassist.responses.CropResponse;
import com.example.farmerassist.responses.CropsActivityResponse;
import com.example.farmerassist.responses.EditLiveStocksResponse;
import com.example.farmerassist.responses.FarmSetupResponse;
import com.example.farmerassist.responses.GetAllCropsResponse;
import com.example.farmerassist.responses.GetAnalyticsResponse;
import com.example.farmerassist.responses.GetCattleResponse;
import com.example.farmerassist.responses.GetFarmResponse;
import com.example.farmerassist.responses.GetPlanticTypeCorp;
import com.example.farmerassist.responses.GetSolideTypeCrop;
import com.example.farmerassist.responses.LoginResponse;
import com.example.farmerassist.responses.WeatherResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface Interface {

    @POST("/api/login")
    Call<LoginResponse> login(@Body Request.LoginRequest request);

    @POST("/api/register")
    Call<CommonResponse> register(@Body Request.RegisterRequest request);

//    @GET("/api/getbycrop/{crop}")
//    Call<AddCropResponse> addcrop(@Body Request.AddcropRequest request);

    @GET("/api/getcropactivities")
    Call<CropResponse> getcropactivities();

    @GET("/api/getcropactivities")
    Call<CommonResponse> sample();

    @GET("/api/getfarmsetup")
    Call<FarmSetupResponse> getfarmsetup();

    @POST("/api/add_product")
    Call<CommonResponse> addproduct(@Body Request.AddproductRequest request);

    @FormUrlEncoded
    @POST("/api/add_income")
    Call<AddIncomeResponse> addIncome(@Field("source_of_income")String soi, @Field("income_amount")String ia, @Field("income_date")String date);

    @GET("api/get_analytics")
    Call<GetAnalyticsResponse> getanalytics(@Query("date") String date);
    @GET("/api/show_livestock")
    Call<GetCattleResponse> getCattle();

    @GET("/api/getAllCrops")
    Call<GetAllCropsResponse> getAllCrops();

    @GET
    Call<GetPlanticTypeCorp> getPlantingCorn(@Url String url);

    @GET
    Call<GetSolideTypeCrop> getSolingTypeCorn(@Url String url);

    @Multipart
    @POST("/api/add_cattel")
    Call<CommonResponse> addLiveStokes(@Part("name") RequestBody name, @Part("birthdate") RequestBody birthDate, @Part("color") RequestBody color,
                                          @Part("feeding_time") RequestBody feedingTime, @Part("vaccinated") RequestBody vaccinated,
                                           @Part("vaccinated_date") RequestBody vaccinatedDate,
                                          @Part MultipartBody.Part image, @Part("gender") RequestBody gender);

    @GET("/api/crop-activities")
    Call<CropsActivityResponse> getCropsActivity(@Query("crop")String crop, @Query("soil_type")String soilType, @Query("planting_type")String plantingType, @Query("date")String date);

    @GET("/api/edit/{id}")
    Call<EditLiveStocksResponse> editLiveStakes(@Path(value = "id", encoded = true)int id);

    @FormUrlEncoded
    @POST("/api/update/{id}")
    Call<CommonResponse> updateLiveStakes(@Path(value = "id", encoded = true)int id, @Field("name") String name,
                                                  @Field("birthdate") String birthDate, @Field("color") String color,
                                                  @Field("feeding_time") String feedingTime, @Field("vaccinated") String vaccinated,
                                                  @Field("vaccinated_date") String vaccinatedDate, @Field("gender") String gender, @Field("doctor_name") String docName,
                                          @Field("prescription") String prescription);
    @GET("2.5/weather")
    Call<WeatherResponse> getWeather(@Query("lat") double lat, @Query("lon") double lon, @Query("appid")String api);

    @POST("/api/add_expense")
    Call<CommonResponse> addExpense(@Query("farm_expense_belongs") String expenseName, @Query("expense_amount_spend") String amount, @Query("expense_date")String date);

}
