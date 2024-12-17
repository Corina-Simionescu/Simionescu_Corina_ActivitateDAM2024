package com.example.test_2_practice_4;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TravelDAO {
    @Insert
    long insert(Travel travel);

    @Query("SELECT * FROM travels")
    List<Travel> getAllTravels();
}
