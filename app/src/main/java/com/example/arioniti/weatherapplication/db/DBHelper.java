package com.example.arioniti.weatherapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.arioniti.weatherapplication.models.Daily;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by niti on 8.2.18..
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final int VERSION = 1;

    //Database name
    public static final String DATABASE_NAME = "weatherBase.db";

    //Home fragment table fields
    public static final String HOME_TABLE = "home_table";
    public static final String TEMPERATURE = "temperature";
    public static final String LOCATION = "location";
    public static final String WEATHER_ICON_PATH = "weather_icon";

    //Daily table fields
    public static final String DAILY_TABLE = "daily_table";
    public static final String TEMP_MAX = "temp_max";
    public static final String TEMP_MIN = "temp_min";
    public static final String CITY_NAME = "city_name";
    public static final String DATE_TIME = "date_time";

    //Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    DateFormat formatter = new SimpleDateFormat("dd-MMM-yy");


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create Home Table query
        String homeQuery = "CREATE TABLE " + HOME_TABLE +
                "(" + "home_table_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + LOCATION + " TEXT,"
                + WEATHER_ICON_PATH + " TEXT,"
                + TEMPERATURE + " REAL"
                + ")";

        //Create Daily table fields query
        String dailyQuery = "CREATE TABLE " + DAILY_TABLE
                + "(" +
                "daily_table_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TEMP_MAX + " REAL,"
                + TEMP_MIN + " REAL,"
                + CITY_NAME + " TEXT,"
                + DATE_TIME + " DATE,"
                + WEATHER_ICON_PATH + " INTEGER"
                + ")";

        //Execute queries
        sqLiteDatabase.execSQL(dailyQuery);
        sqLiteDatabase.execSQL(homeQuery);
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql_delete_daily = "DROP TABLE " + DAILY_TABLE + ";";
        String sql_delete_home = "DROP TABLE " + HOME_TABLE + ";";
        sqLiteDatabase.execSQL(sql_delete_daily);
        sqLiteDatabase.execSQL(sql_delete_home);
        onCreate(sqLiteDatabase);
    }

    public void createHomeModelItem(HomeModel item) {
        ContentValues values = new ContentValues(3);

        values.put(LOCATION, item.getLocationName());
        values.put(WEATHER_ICON_PATH, item.getWeatherIconPath());
        values.put(TEMPERATURE, item.getTemp());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(HOME_TABLE, null, values);

    }

    public HomeModel getHomeModel() {
        String sql = "SELECT *  FROM " + HOME_TABLE + ";";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);
        HomeModel homeModel = null;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            homeModel = new HomeModel(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getDouble(3));
        }
        return homeModel;
    }

    public void createDailyModel(ArrayList<Daily> daily) {
        ContentValues values = new ContentValues();
        for (int i = 0; i < daily.size(); i++) {
            values.put(TEMP_MAX, daily.get(i).getTempMax());
            values.put(TEMP_MIN, daily.get(i).getTempMin());
            values.put(CITY_NAME, daily.get(i).getName());
            values.put(DATE_TIME, formatter.format(daily.get(i).getDate()));
            values.put(WEATHER_ICON_PATH, daily.get(i).getIdIcon());
            SQLiteDatabase db = this.getWritableDatabase();
            db.insert(DAILY_TABLE, null, values);
        }
    }

    public ArrayList<Daily> getDailyArrayList() {

        String sql = "Select * from " + DAILY_TABLE + ";";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<Daily> temp = new ArrayList<>();

        Daily daily = null;
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    try {
                        daily = new Daily(
                                cursor.getDouble(1),
                                cursor.getDouble(2),
                                cursor.getString(3),
                                formatter.parse(cursor.getString(4)),
                                cursor.getString(5)
                        );
                        temp.add(daily);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } while (cursor.moveToNext());

            }
        }

        return temp;
    }


}
