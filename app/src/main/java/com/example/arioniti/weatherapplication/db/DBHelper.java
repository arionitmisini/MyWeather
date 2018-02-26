package com.example.arioniti.weatherapplication.db;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by niti on 8.2.18..
 */

public class DBHelper {

    public static final int VERSION = 1;

    //Database name
    public static final String DATABASE_NAME = "weatherBase.db";

    //Home fragment table fields
    public static final String TEMPERATURE = "temperature";
    public static final String LOCATION = "location";
    public static final String WEATHER_ICON = "weather_icon";

    //Daily table fields
    public static final String TEMP_MAX = "temp_max";
    public static final String TEMP_MIN = "temp_min";
    public static final String CITY_NAME = "city_name";
    public static final String DATE_TIME = "date_time";
    public static final String ICON_PATH = "icon_path";


    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }


    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
