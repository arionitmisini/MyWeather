package com.example.arioniti.weatherapplication.models;


import android.support.annotation.NonNull;

import org.apache.commons.lang3.time.DateUtils;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Created by Arioniti on 1/29/2018.
 */

public class Daily implements Comparable<Daily> {
    private double tempMax;
    private double tempMin;
    private String name;
    private Date date;
    private String idIcon;
    DecimalFormat df = new DecimalFormat("##.##");


    public Daily(double tempMax, double tempMin, String name, Date date, String idIcon) {
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.name = name;
        this.date = date;
        this.idIcon = idIcon;
    }

    public String getIdIcon() {
        return idIcon;
    }

    public void setIdIcon(String idIcon) {
        this.idIcon = idIcon;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public double getTempMin() {
        double min = Double.parseDouble(df.format(tempMin));
        return min;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {

        double max = Double.parseDouble(df.format(tempMax));
        return max;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object obj) {
        {
            if (obj instanceof Daily) {
                Daily d = (Daily) obj;
                if (DateUtils.isSameDay(d.getDate(), date)) ;
                return true;
            }
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }

    @Override
    public int compareTo(@NonNull Daily daily) {
        return getDate().compareTo(daily.getDate());
    }
}
