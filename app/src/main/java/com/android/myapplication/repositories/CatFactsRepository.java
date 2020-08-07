package com.android.myapplication.repositories;

import android.content.Context;

import com.android.myapplication.models.CatFactModel;
import com.android.myapplication.network.CatFactsApiClient;
import com.android.myapplication.persistance.CatFactsDao;
import com.android.myapplication.persistance.CatFactsRoomDatabase;

import java.util.List;

import androidx.lifecycle.LiveData;

public class CatFactsRepository {
    private static CatFactsRepository instance;
    private CatFactsDao mCatFactsDao;
    private CatFactsApiClient mCatFactsApiClient;

    public static CatFactsRepository getInstance(Context context) {
        if (instance == null) {
            instance = new CatFactsRepository(context);
        }
        return instance;
    }

    private CatFactsRepository(Context context) {
        mCatFactsApiClient = CatFactsApiClient.getInstance();
        mCatFactsDao = CatFactsRoomDatabase.getDatabase(context).catFactsDao();
    }

    public LiveData<List<CatFactModel>> getCatFacts() {
        return mCatFactsApiClient.getCatFacts();
    }

//    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
//    // that you're not doing any long running operations on the main thread, blocking the UI.
//    public void insert(CatFact catFact) {
//        CatFactsRoomDatabase.databaseWriteExecutor.execute(() -> {
//            mCatFactsDao.insert(catFact);
//        });
//    }


}
