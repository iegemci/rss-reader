package com.enesgemci.rssreader.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.enesgemci.rssreader.R;
import com.enesgemci.rssreader.base.BaseActivity;
import com.enesgemci.rssreader.ui.article.ArticleFragment;

public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, ArticleFragment.newInstance())
                .commit();
    }

    @NonNull
    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }
}
