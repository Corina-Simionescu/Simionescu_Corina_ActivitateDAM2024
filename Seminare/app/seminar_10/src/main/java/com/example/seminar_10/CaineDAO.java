package com.example.seminar_10;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CaineDAO {
    @Insert
    long insert(Caine caine);

    @Query("SELECT * FROM caini")
    List<Caine> getAllCaini();

    @Update
    void update(Caine caine);

    @Delete
    void delete(Caine caine);

    @Query("DELETE FROM caini")
    void deleteAll();

    @Query("SELECT * FROM caini WHERE id = :id")
    Caine getCaineById(long id);
}