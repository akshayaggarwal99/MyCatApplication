package com.android.myapplication.network;

import android.util.Log;

import com.android.myapplication.AppExecutors;
import com.android.myapplication.models.CatFactModel;
import com.android.myapplication.network.responses.AllCatFactsResponse;
import com.android.myapplication.viewmodel.CatFactsListViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Response;

import static com.android.myapplication.utils.Constants.CONNECTION_TIMEOUT;

public class CatFactsApiClient {

    private static final String TAG = "CatFactsApiClient";
    private static CatFactsApiClient instance;
    private MutableLiveData<List<CatFactModel>> mCatFacts;
    private RetrieveCatFactsRunnable retrieveCatFactsRunnable;

    public static CatFactsApiClient getInstance() {
        if (instance == null) {
            instance = new CatFactsApiClient();
        }
        return instance;
    }

    private CatFactsApiClient() {
        mCatFacts = new MutableLiveData<>();
        getCatFactsApi();
    }

    public LiveData<List<CatFactModel>> getCatFacts() {
        return mCatFacts;
    }

    public void getCatFactsApi() {
        if (retrieveCatFactsRunnable != null) {
            retrieveCatFactsRunnable = null;
        }
        retrieveCatFactsRunnable = new RetrieveCatFactsRunnable();
        final Future handler = AppExecutors.getInstance().networkIO().submit(retrieveCatFactsRunnable);
        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //Let the user know its timed out
                handler.cancel(true);
            }
        }, CONNECTION_TIMEOUT, TimeUnit.SECONDS);

    }

    private class RetrieveCatFactsRunnable implements Runnable {
        boolean cancelRequest;

        public RetrieveCatFactsRunnable() {
            cancelRequest = false;
        }

        @Override
        public void run() {
            try {
                Response<AllCatFactsResponse> response = getCatFacts().execute();
                if (cancelRequest) {
                    return;
                }
                if (response.code() == 200) {
                    assert response.body() != null;
                    List<CatFactModel> catFactModelList = new ArrayList<>(response.body().getCatFactModels());
                    mCatFacts.postValue(catFactModelList);

                } else {
                    String error = response.errorBody().string();
                    Log.e(TAG, "run: " + error);
                    mCatFacts.postValue(null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                mCatFacts.postValue(null);
            }

        }

        private Call<AllCatFactsResponse> getCatFacts() {
            return ServiceGenerator.getCatFactsApi().getCatFacts();
        }

        private void cancelRequest() {
            Log.d(TAG, "cancelRequest: cancelling request");
            cancelRequest = true;
        }
    }
}
