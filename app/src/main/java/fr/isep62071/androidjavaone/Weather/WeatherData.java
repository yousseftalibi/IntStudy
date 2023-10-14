package fr.isep62071.androidjavaone.Weather;

import java.util.List;

public class WeatherData {
    public double lat;
    public double lon;
    public String timezone;
    public long timezone_offset;
    public CurrentWeather current;
    public List<MinutelyWeather> minutely;
    public List<HourlyWeather> hourly;
    public List<DailyWeather> daily;
    public List<WeatherAlert> alerts;

    public static class CurrentWeather {
        public long dt;
        public long sunrise;
        public long sunset;
        public double temp;
        public double feels_like;
        public int pressure;
        public int humidity;
        public double dew_point;
        public double uvi;
        public int clouds;
        public int visibility;
        public double wind_speed;
        public int wind_deg;
        public List<WeatherDescription> weather;
        public RainInfo rain;
    }

    public static class WeatherDescription {
        public int id;
        public String main;
        public String description;
        public String icon;
    }

    public static class RainInfo {
        public double h1;
    }

    public static class MinutelyWeather {
        public long dt;
        public double precipitation;
    }

    public static class HourlyWeather {
        public long dt;
        public double temp;
        public double feels_like;
        public int pressure;
        public int humidity;
        public double dew_point;
        public double uvi;
        public int clouds;
        public int visibility;
        public double wind_speed;
        public int wind_deg;
        public double wind_gust;
        public List<WeatherDescription> weather;
        public double pop;
    }

    public static class DailyWeather {
        public long dt;
        public long sunrise;
        public long sunset;
        public long moonrise;
        public long moonset;
        public double moon_phase;
        public TemperatureInfo temp;
        public FeelsLike feels_like;
        public int pressure;
        public int humidity;
        public double dew_point;
        public double wind_speed;
        public int wind_deg;
        public List<WeatherDescription> weather;
        public int clouds;
        public double pop;
        public double rain;
        public double uvi;
    }

    public static class TemperatureInfo {
        public double day;
        public double min;
        public double max;
        public double night;
        public double eve;
        public double morn;
    }

    public static class FeelsLike {
        public double day;
        public double night;
        public double eve;
        public double morn;
    }

    public static class WeatherAlert {
        public String sender_name;
        public String event;
        public long start;
        public long end;
        public String description;
        public List<String> tags;
    }
}
