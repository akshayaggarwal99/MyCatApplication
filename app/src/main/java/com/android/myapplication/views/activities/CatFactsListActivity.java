package com.android.myapplication.views.activities;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.myapplication.R;
import com.android.myapplication.persistance.CatFact;
import com.android.myapplication.utils.Resource;
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
    List<CatFact> mCatFacts = new ArrayList<>();

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

    private void initRecyclerView() {
        catFactRecyclerViewAdapter = new CatFactRecyclerViewAdapter();
        mRecyclerView.setAdapter(catFactRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        catFactRecyclerViewAdapter.setOnItemClickListener((position, v) -> {
            Intent intent = new Intent(CatFactsListActivity.this, CatFactDetailsActivity.class);
            intent.putExtra("cat_fact", mCatFacts.get(position));
            startActivity(intent);
        });

    }

    private void subscribeObservers() {

        catFactsListViewModel.getAllCatFacts().observe(this, new Observer<Resource<List<CatFact>>>() {
            @Override
            public void onChanged(Resource<List<CatFact>> listResource) {
                if (listResource != null) {
                    Log.d(TAG, "onChanged: status " + listResource.status);
                    if (listResource.status == Resource.Status.SUCCESS && listResource.data != null) {
                        progressBar.setVisibility(View.GONE);
                        mCatFacts.clear();
                        mCatFacts.addAll(listResource.data);
                        catFactRecyclerViewAdapter.setCatFactModelList(listResource.data);
                        Log.d(TAG, "onChanged: data " + listResource.data);
                    } else if (listResource.status == Resource.Status.ERROR && listResource.data != null) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(CatFactsListActivity.this, listResource.message, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}