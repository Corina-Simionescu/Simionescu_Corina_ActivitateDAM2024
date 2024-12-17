package com.example.test_2_practice_3;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Om.class}, version = 1)
public abstract class OmDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "oameni_database";
    private static OmDatabase instance; 

    public abstract OmDAO omDAO();

    public static synchronized OmDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            OmDatabase.class,
                            DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
