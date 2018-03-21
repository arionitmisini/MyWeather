package com.example.arioniti.weatherapplication.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by niti on 3/20/18.
 */

@Database(entities = {HomeModel.class},version = 1)

public abstract class AppDatabase  extends RoomDatabase {

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext()
            ,AppDatabase.class,"app-database")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public static void destroyInstance(){
        instance = null;
    }

}
