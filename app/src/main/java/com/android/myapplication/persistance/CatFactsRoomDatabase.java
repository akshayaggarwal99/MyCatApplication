package com.android.myapplication.persistance;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {CatFact.class}, version = 1, exportSchema = false)
public abstract class CatFactsRoomDatabase extends RoomDatabase {
    public abstract CatFactsDao catFactsDao();

    public static final String DATABASE_NAME = "cat_fact_database";

    private static volatile CatFactsRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static CatFactsRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CatFactsRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CatFactsRoomDatabase.class, DATABASE_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
