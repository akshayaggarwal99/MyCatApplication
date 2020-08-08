package com.android.myapplication.views.activities;

import android.annotation.SuppressLint;

import com.android.myapplication.R;
import com.android.myapplication.persistance.CatFact;
import com.android.myapplication.views.base.BaseActivity;

import androidx.appcompat.widget.AppCompatTextView;
import butterknife.BindView;

public class CatFactDetailsActivity extends BaseActivity {

    CatFact catFact;
    @BindView(R.id.tv_cat_fact)
    AppCompatTextView tvCatFact;
    @BindView(R.id.tv_username)
    AppCompatTextView tvUserName;
    @BindView(R.id.tv_upvotes)
    AppCompatTextView tvUpvotes;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_cat_fact_details;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void init() {
        catFact = getIntent().getParcelableExtra("cat_fact");
        tvCatFact.setText(catFact.getText());
        tvUserName.setText("by " + catFact.getFirstName() + " " + catFact.getLastName());
        tvUpvotes.setText(catFact.getUpvotes().toString() + " upvotes");
    }
}