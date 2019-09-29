package com.enesgemci.rssreader.ui.article;

import com.enesgemci.rssreader.base.BasePresenter;
import com.enesgemci.rssreader.network.interactor.RssInteractor;
import com.enesgemci.rssreader.rss.Article;
import com.enesgemci.rssreader.rss.OnTaskCompleteListener;

import java.util.List;

class ArticlePresenter extends BasePresenter<ArticleView> {

    private RssInteractor interactor;

    ArticlePresenter(RssInteractor interactor) {
        this.interactor = interactor;
    }

    void getArticles() {
        interactor.getArticles(new OnTaskCompleteListener() {
            @Override
            public void onTaskCompleted(List<Article> articles) {
                if (isViewAttached()) {
                    getView().setArticles(articles);
                }
            }

            @Override
            public void onError() {

            }
        });
    }
}
