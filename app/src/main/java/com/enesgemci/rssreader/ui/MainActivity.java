package com.enesgemci.rssreader.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.enesgemci.rssreader.R;
import com.enesgemci.rssreader.base.BaseActivity;

public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @NonNull
    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }
}
