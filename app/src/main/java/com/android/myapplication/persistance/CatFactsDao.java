package com.android.myapplication.persistance;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface CatFactsDao {

    // allowing the insert of the same cat_fact multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CatFact catFact);

    @Update
    void update(CatFact catFact);

    @Query("DELETE FROM cat_fact_table")
    void deleteAll();

    @Query("SELECT * from cat_fact_table ORDER BY id ASC")
    LiveData<List<CatFact>> getAllCatFacts();
}
