package com.android.myapplication.views.activities;

import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;

import com.android.myapplication.R;
import com.android.myapplication.models.CatFactModel;
import com.android.myapplication.viewmodel.CatFactsListViewModel;
import com.android.myapplication.views.adapters.CatFactRecyclerViewAdapter;
import com.android.myapplication.views.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class CatFactsListActivity extends BaseActivity {

    private static final String TAG = "CatFactsListActivity";

    private CatFactsListViewModel catFactsListViewModel;

    @BindView(R.id.rv_facts)
    RecyclerView mRecyclerView;
    @BindView(R.id.progress_circular)
    ProgressBar progressBar;

    CatFactRecyclerViewAdapter catFactRecyclerViewAdapter;
    List<CatFactModel> catFactModelList = new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.activity_cat_facts_list;
    }

    @Override
    protected void init() {
        // test.setOnClickListener(this::onClick);
        catFactsListViewModel = ViewModelProviders.of(this).get(CatFactsListViewModel.class);
        progressBar.setVisibility(View.VISIBLE);
        initRecyclerView();
        subscribeObservers();
    }

//    private void testRetrofitRequest() {
//        CatFactsApi catFactsApi = ServiceGenerator.getCatFactsApi();
//        Call<AllCatFactsResponse> responseCall = catFactsApi.getCatFacts();
//
//        responseCall.enqueue(new Callback<AllCatFactsResponse>() {
//            @Override
//            public void onResponse(Call<AllCatFactsResponse> call, Response<AllCatFactsResponse> response) {
//                Log.d(TAG, "onResponse: server response: " + response.toString());
//                if (response.code() == 200) {
//                    Log.d(TAG, "onResponse: " + response.body().toString());
//                    List<CatFactModel> catFactModels = new ArrayList<>(response.body().getCatFactModels());
//                    for (CatFactModel catFactModel : catFactModels) {
//                        Log.d(TAG, "onResponse: " + catFactModel.getText());
//
//                    }
//                } else {
//                    try {
//                        Log.d(TAG, "onResponse: " + response.errorBody().string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<AllCatFactsResponse> call, Throwable t) {
//
//            }
//        });
//    }
//
//    private void onClick(View view) {
//        testRetrofitRequest();
//    }

    private void initRecyclerView() {
        catFactRecyclerViewAdapter = new CatFactRecyclerViewAdapter();
        mRecyclerView.setAdapter(catFactRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        catFactRecyclerViewAdapter.setOnItemClickListener((position, v) -> {
            Intent intent = new Intent(CatFactsListActivity.this, CatFactDetailsActivity.class);
            intent.putExtra("cat_fact_model", catFactModelList.get(position));
            intent.putExtra("cat_fact_user_name_model", catFactModelList.get(position).getUser().getUserNameModel());
            startActivity(intent);
        });

    }

    private void subscribeObservers() {
        catFactsListViewModel.getAllCatFacts().observe(this, new Observer<List<CatFactModel>>() {
            @Override
            public void onChanged(List<CatFactModel> catFactModels) {
                progressBar.setVisibility(View.GONE);
                if (catFactModels != null) {
                    catFactModelList.clear();
                    catFactModelList.addAll(catFactModels);
                    catFactRecyclerViewAdapter.setCatFactModelList(catFactModels);
                }
            }
        });
    }
}