package com.enesgemci.rssreader.ui.article;

import com.enesgemci.rssreader.base.BaseView;
import com.enesgemci.rssreader.rss.Article;

import java.util.ArrayList;

public interface ArticleView extends BaseView {

    void setArticles(ArrayList<Article> articles);

    void onResponseError();

    void openArticle(String link);
}
