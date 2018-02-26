
package com.example.arioniti.weatherapplication.fivedays;

import com.google.gson.annotations.SerializedName;

public class Main {


    private double temp;
    @SerializedName("temp_min")
    private double temp_Min;
    @SerializedName("temp_max")
    private double temp_Max;

    private double pressure;

    private double seaLevel;

    private double grndLevel;

    private int humidity;

    private int tempKf;

    public double getTemp() {
        return this.temp - 273.15;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getTempMin() {
        return this.temp_Min - 273.15;
    }

    public void setTempMin(double tempMin) {
        this.temp_Min = tempMin;
    }

    public double getTempMax() {
        return this.temp_Max - 273.15;
    }

    public void setTempMax(double tempMax) {
        this.temp_Max = tempMax;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(double seaLevel) {
        this.seaLevel = seaLevel;
    }

    public double getGrndLevel() {
        return grndLevel;
    }

    public void setGrndLevel(double grndLevel) {
        this.grndLevel = grndLevel;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getTempKf() {
        return tempKf;
    }

    public void setTempKf(int tempKf) {
        this.tempKf = tempKf;
    }

}
