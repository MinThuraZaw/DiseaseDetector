package com.example.diseasedetector.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {CheckupRecords.class}, exportSchema = false, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DB_NAME = "histroy_db";
    public static final String TABLE_NAME = "histroy";

    private static AppDatabase instance;

//    public static synchronized AppDatabase getInstance(Context context){
//
//        if(instance == null){
//            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
//                    .fallbackToDestructiveMigration()
//                    .allowMainThreadQueries()
//                    .build();
//        }
//
//
//
//        return instance;
//    }



    public abstract HistroyDao histroyDao();
}