package fr.isep62071.androidjavaone.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface  WeatherService {
    @GET("data/3.0/onecall")
    Call<WeatherData> getWeatherByCity(@Query("lat") double lat, @Query("lon") double lon, @Query("appid") String apiKey);

}
