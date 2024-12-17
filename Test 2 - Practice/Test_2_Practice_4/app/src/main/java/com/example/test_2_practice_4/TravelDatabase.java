package com.example.test_2_practice_4;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Travel.class}, version = 1)
public abstract class TravelDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "travels_database";
    private static TravelDatabase instance;

    public abstract TravelDAO travelDAO();

    public static synchronized TravelDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            TravelDatabase.class,
                            DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
