package com.example.arioniti.weatherapplication.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.text.DecimalFormat;

/**
 * Created by niti on 2/26/18.
 */
@Entity
public class HomeModel {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int homeModelId;
    @ColumnInfo
    private String locationName;
    @ColumnInfo
    private String weatherIconPath;
    @ColumnInfo
    private double temp;



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
        DecimalFormat df = new DecimalFormat("##.#");
        return Double.parseDouble(df.format(temp));
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public int getHomeModelId() {
        return homeModelId;
    }

    public void setHomeModelId(@NonNull int homeModelId) {
        this.homeModelId = homeModelId;
    }
}
