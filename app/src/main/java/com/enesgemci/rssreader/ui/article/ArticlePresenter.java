package com.enesgemci.rssreader.ui.article;

import com.enesgemci.rssreader.base.BasePresenter;
import com.enesgemci.rssreader.network.interactor.RssInteractor;

class ArticlePresenter extends BasePresenter<ArticleView> {

    private RssInteractor interactor;

    ArticlePresenter(RssInteractor interactor) {
        this.interactor = interactor;
    }
}
