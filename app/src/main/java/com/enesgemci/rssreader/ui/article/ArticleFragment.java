package com.enesgemci.rssreader.ui.article;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.enesgemci.rssreader.R;
import com.enesgemci.rssreader.base.BaseFragment;
import com.enesgemci.rssreader.di.GraphFactory;
import com.enesgemci.rssreader.network.interactor.RssInteractor;
import com.enesgemci.rssreader.rss.Article;

import java.util.List;

public class ArticleFragment extends BaseFragment<ArticleView, ArticlePresenter> implements ArticleView {

    public static ArticleFragment newInstance() {
        return new ArticleFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPresenter().getArticles();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_articles;
    }

    @NonNull
    @Override
    protected ArticlePresenter createPresenter() {
        RssInteractor interactor = GraphFactory.getInstance().provideInteractor();
        return new ArticlePresenter(interactor);
    }

    @Override
    public void setArticles(List<Article> articles) {
        // TODO:
    }
}
