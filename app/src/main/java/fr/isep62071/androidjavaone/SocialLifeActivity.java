package fr.isep62071.androidjavaone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import fr.isep62071.androidjavaone.Weather.WeatherData;
import fr.isep62071.androidjavaone.Weather.WeatherService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SocialLifeActivity extends Activity {
    private TextView Country_Info;
    private TextView country_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_social_life);
        Country_Info = (TextView) findViewById(R.id.Country_Info);
        country_name = (TextView) findViewById(R.id.country_name);
        Button returnButton = findViewById(R.id.return_button2);
        Button todo_Button = findViewById(R.id.todo_button);

        returnButton.setOnClickListener(v -> {
            Intent intent = new Intent(SocialLifeActivity.this, PaperworkActivity.class);
            startActivity(intent);
            finish();
        });

        todo_Button.setOnClickListener(v -> {
            Intent intent = new Intent(SocialLifeActivity.this, RexActivity.class);
            startActivity(intent);
            finish();
        });

        Intent intent = getIntent();
        String destination = intent.getStringExtra("DESTINATION").toLowerCase();

        country_name.setText(destination);

        try (InputStream inputStream = getAssets().open(destination+".txt");
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append('\n');
            }

            Country_Info.setText(stringBuilder.toString());

        } catch (IOException e) {
            e.printStackTrace();
            Country_Info.setText("Error reading file!");
        }

        String apiKey = "d089df8ed9d161d9851d856325e022af";

        double lat =0;double lon=0;

        switch (destination.toLowerCase()){
            case "argentina":
                lat = -34; lon = -64;
                break;
            case "czech":
                lat = 50.9027; lon = 19.602;
                break;
            case "tunisia":
                lat = 34; lon = 9;
                break;
            case "india":
                lat = 20; lon = 77;
                break;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherService service = retrofit.create(WeatherService.class);
        Call<WeatherData> call = service.getWeatherByCity(lat, lon, apiKey);
        call.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {

                if (response.isSuccessful()) {
                    String description = response.body().current.weather.get(0).description;
                    String feels_like = String.format("%.2f°C",response.body().daily.get(0).feels_like.day - 273.15);
                    String maxTemp =  String.format("%.2f°C",response.body().daily.get(0).temp.max - 273.15);

                    Country_Info.append("\n Description of weather: " + description);
                    Country_Info.append("\n Today, it feels like "+ feels_like + " with a max temperature of "+ maxTemp );

                } else {
                    Country_Info.append(call.request().url().toString());
                    Country_Info.append(response.toString());

                    Country_Info.append("\nFailed to fetch weather data.");
                }
            }
            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                Country_Info.append("\nFailed to fetch weather data.");
            }
        });
    }
}