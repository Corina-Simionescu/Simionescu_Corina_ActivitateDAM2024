package com.example.seminar_11;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.seminar_11.Caine;

@Database(entities = {Caine.class}, version = 1)
public abstract class CaineDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "caini_database";
    private static CaineDatabase instance;

    public abstract CaineDAO caineDAO();

    public static synchronized CaineDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            CaineDatabase.class,
                            DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}