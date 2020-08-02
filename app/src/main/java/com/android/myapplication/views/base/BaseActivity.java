package com.android.myapplication.views.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {
    protected Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());

        unbinder = ButterKnife.bind(this);
        init();
    }

    protected abstract int setLayoutId();

    protected abstract void init();

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unbinder.unbind();
    }
}
