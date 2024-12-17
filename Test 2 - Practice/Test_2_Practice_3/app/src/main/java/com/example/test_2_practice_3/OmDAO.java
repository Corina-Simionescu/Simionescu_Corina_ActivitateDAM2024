package com.example.test_2_practice_3;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface OmDAO {
    @Insert
    long insert(Om om);

    @Query("SELECT * FROM oameni")
    List<Om> getAllOameni();
}
