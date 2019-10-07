package com.example.diseasedetector.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HistroyDao {

    @Query("SELECT * FROM CheckupRecords")
    List<CheckupRecords> getAll();

    @Query("SELECT * FROM CheckupRecords WHERE dtype IN (:dtype)")
    List<CheckupRecords> loadAllBydType(int[] dtype);

    @Query("SELECT count(*) from CheckupRecords")
    int dataCount();

//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    User findByName(String first, String last);

    @Insert
    void insertAll(CheckupRecords... checkupRecords);

    @Insert
    void insertHistroy(CheckupRecords checkupRecords);

    @Delete
    void delete(CheckupRecords checkupRecords);

}
