package com.example.arioniti.weatherapplication.dal;



import com.example.arioniti.weatherapplication.fivedays.FiveDaysWeatherModel;
import com.example.arioniti.weatherapplication.fivedays.WeatherAPIResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
/**
 * Created by Arioniti on 19/01/18.
 */
public interface APIInterface {
        @GET("data/2.5/weather")
        Call<WeatherAPIResult> getWeatherReq(@Query("lat") double Latitude, @Query("lon") double Longitude, @Query("appid") String appID);

        @GET("data/2.5/forecast")
        Call<FiveDaysWeatherModel> getFiveWeatherReq(@Query("lat") double Latitude, @Query("lon") double Longitude, @Query("appid") String appID);
}
