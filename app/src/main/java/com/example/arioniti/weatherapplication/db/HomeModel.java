package com.example.arioniti.weatherapplication.db;

import java.text.DecimalFormat;

/**
 * Created by niti on 2/26/18.
 */

public class HomeModel {

    private String locationName;
    private String weatherIconPath;
    private double temp;
    private DecimalFormat df = new DecimalFormat("##.#");


    public HomeModel(String locationName, String weatherIconPath, double temp) {
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

    public String getWeatherIconPath() {
        return weatherIconPath;
    }

    public void setWeatherIconPath(String weatherIconPath) {
        this.weatherIconPath = weatherIconPath;
    }

    public double getTemp() {
        return Double.parseDouble(df.format(temp));
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }
}
