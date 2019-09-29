package com.enesgemci.rssreader.ui.article;

import com.enesgemci.rssreader.base.BasePresenter;
import com.enesgemci.rssreader.di.GraphFactory;
import com.enesgemci.rssreader.network.interactor.OnTaskCompleteListener;
import com.enesgemci.rssreader.network.interactor.RssInteractor;
import com.enesgemci.rssreader.rss.Article;

import java.util.ArrayList;

class ArticlePresenter extends BasePresenter<ArticleView> {

    private final RssInteractor interactor;

    ArticlePresenter() {
        this.interactor = GraphFactory.getInstance().provideInteractor();
    }

    void getArticles() {
        interactor.getArticles(new OnTaskCompleteListener() {
            @Override
            public void onTaskCompleted(ArrayList<Article> articles) {
                if (isViewAttached()) {
                    getView().setArticles(articles);
                }
            }

            @Override
            public void onError() {
                if (isViewAttached()) {
                    getView().onResponseError();
                }
            }
        });
    }

    public void onArticleClicked(Article article) {
        if (isViewAttached()) {
            getView().openArticle(article.getLink());
        }
    }
}
