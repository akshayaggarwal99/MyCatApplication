package com.android.myapplication.network;

import com.android.myapplication.network.responses.AllCatFactsResponse;
import com.android.myapplication.network.responses.ApiResponse;

import androidx.lifecycle.LiveData;
import retrofit2.http.GET;

public interface CatFactsApi {

    @GET("/facts")
    LiveData<ApiResponse<AllCatFactsResponse>> getCatFacts();
}
