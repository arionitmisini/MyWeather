
package com.example.arioniti.weatherapplication.fivedays;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind {


    private double speed;

    private double deg;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDeg() {
        return deg;
    }

    public void setDeg(double deg) {
        this.deg = deg;
    }

}
