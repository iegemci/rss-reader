package com.enesgemci.rssreader.ui.article;

import com.enesgemci.rssreader.base.BaseView;
import com.enesgemci.rssreader.rss.Article;

import java.util.List;

public interface ArticleView extends BaseView {

    void setArticles(List<Article> articles);
}
