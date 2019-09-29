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

        ArticleFragment fragment;

        if (savedInstanceState == null) {
            fragment = ArticleFragment.newInstance();
        } else {
            fragment = (ArticleFragment)
                    getSupportFragmentManager().findFragmentByTag(ArticleFragment.TAG);
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment, ArticleFragment.TAG)
                .commit();
    }

    @NonNull
    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }
}
