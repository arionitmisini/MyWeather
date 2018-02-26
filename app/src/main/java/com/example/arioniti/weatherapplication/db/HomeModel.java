package com.example.arioniti.weatherapplication.db;

/**
 * Created by niti on 2/26/18.
 */

public class HomeModel {

    private String locationName;
    private int weatherIconPath;
    private double temp;

    public HomeModel(String locationName, int weatherIconPath, double temp) {
        this.locationName = locationName;
        this.weatherIconPath = weatherIconPath;
        this.temp = temp;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public int getWeatherIconPath() {
        return weatherIconPath;
    }

    public void setWeatherIconPath(int weatherIconPath) {
        this.weatherIconPath = weatherIconPath;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }
}
