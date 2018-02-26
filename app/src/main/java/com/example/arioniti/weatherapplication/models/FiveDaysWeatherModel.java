package com.example.arioniti.weatherapplication.models;

import java.util.List;

public class FiveDaysWeatherModel {


    private String cod;

    private double message;

    private int cnt;

    private List<ListWeather> list;

    private City city;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<ListWeather> getList() {
        return list;
    }

    public void setList(List<ListWeather> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

}
