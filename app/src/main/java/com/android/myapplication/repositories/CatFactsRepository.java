package com.android.myapplication.repositories;

import android.content.Context;
import android.util.Log;

import com.android.myapplication.AppExecutors;
import com.android.myapplication.models.CatFactModel;
import com.android.myapplication.network.ServiceGenerator;
import com.android.myapplication.network.responses.AllCatFactsResponse;
import com.android.myapplication.network.responses.ApiResponse;
import com.android.myapplication.persistance.CatFact;
import com.android.myapplication.persistance.CatFactsDao;
import com.android.myapplication.persistance.CatFactsRoomDatabase;
import com.android.myapplication.utils.Constants;
import com.android.myapplication.utils.NetworkBoundResource;
import com.android.myapplication.utils.Resource;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

public class CatFactsRepository {

    private static final String TAG = "CatFactsRepository";
    private static CatFactsRepository instance;
    private CatFactsDao mCatFactsDao;

    public static CatFactsRepository getInstance(Context context) {
        if (instance == null) {
            instance = new CatFactsRepository(context);
        }
        return instance;
    }

    private CatFactsRepository(Context context) {
        mCatFactsDao = CatFactsRoomDatabase.getDatabase(context).catFactsDao();
    }

    public LiveData<Resource<List<CatFact>>> getCatFacts() {
        return new NetworkBoundResource<List<CatFact>, AllCatFactsResponse>(AppExecutors.getInstance()) {

            @Override
            protected void saveCallResult(@NonNull AllCatFactsResponse item) {
                if (item.getCatFactModels() != null) {
                    List<CatFact> catFacts = catFactsFromCatFactModel(item.getCatFactModels());
                    mCatFactsDao.insertCatFacts(catFacts);

                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<CatFact> data) {
                //check here timestamp to check last refresh time
                if (data != null && data.size() > 0) {
                    int currentTime = (int) (System.currentTimeMillis() / 1000);
                    Log.d(TAG, "shouldFetch: current time: " + currentTime);
                    int lastRefresh = data.get(0).getTimestamp();
                    Log.d(TAG, "shouldFetch: last refresh: " + lastRefresh);
                    Log.d(TAG, "shouldFetch: it's been " + ((currentTime - lastRefresh) / 60 / 60 / 24) +
                            " days since these facts were refreshed. 30 days must elapse before refreshing. ");
                    if ((currentTime - data.get(0).getTimestamp()) >= Constants.FACTS_REFRESH_TIME) {
                        Log.d(TAG, "shouldFetch: SHOULD REFRESH FACTS?! " + true);
                        return true;
                    }
                    Log.d(TAG, "shouldFetch: SHOULD REFRESH FACTS?! " + false);
                    return false;
                } else {
                    return true;
                }
            }

            @NonNull
            @Override
            protected LiveData<List<CatFact>> loadFromDb() {
                return mCatFactsDao.getAllCatFacts();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<AllCatFactsResponse>> createCall() {

                return ServiceGenerator.getCatFactsApi().getCatFacts();
            }
        }.

                getAsLiveData();
    }

    public List<CatFact> catFactsFromCatFactModel(List<CatFactModel> catFactModels) {
        List<CatFact> catFacts = new ArrayList<>();
        for (CatFactModel catFactModel : catFactModels) {
            if (catFactModel.getUser() != null && catFactModel.getUser().getUserNameModel() != null) {
                CatFact catFact = new CatFact(catFactModel.get_id(), catFactModel.getText(),
                        catFactModel.getType(), catFactModel.getUser().getUserNameModel().getFirst(),
                        catFactModel.getUser().getUserNameModel().getLast(), catFactModel.getUpvotes()
                        , (int) (System.currentTimeMillis() / 1000));
                catFacts.add(catFact);
            }
        }
        return catFacts;
    }

//    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
//    // that you're not doing any long running operations on the main thread, blocking the UI.
//    public void insert(CatFact catFact) {
//        CatFactsRoomDatabase.databaseWriteExecutor.execute(() -> {
//            mCatFactsDao.insert(catFact);
//        });
//    }


}
