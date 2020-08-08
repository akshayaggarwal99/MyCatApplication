package com.android.myapplication.network;

import com.android.myapplication.utils.Constants;
import com.android.myapplication.utils.LiveDataCallAdapter;
import com.android.myapplication.utils.LiveDataCallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.android.myapplication.utils.Constants.CONNECTION_TIMEOUT;
import static com.android.myapplication.utils.Constants.READ_TIMEOUT;
import static com.android.myapplication.utils.Constants.WRITE_TIMEOUT;


public class ServiceGenerator {
//    private static OkHttpClient client = new OkHttpClient.Builder()
//
//            // establish connection to server
//            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
//
//            // time between each byte read from the server
//            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
//
//            // time between each byte sent to server
//            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
//
//            .retryOnConnectionFailure(false)
//
//            .build();


    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static CatFactsApi catFactsApi = retrofit.create(CatFactsApi.class);

    public static CatFactsApi getCatFactsApi() {
        return catFactsApi;
    }
}
