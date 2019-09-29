package com.enesgemci.rssreader.network.interactor;

import com.enesgemci.rssreader.rss.Article;

import java.util.ArrayList;

public interface OnTaskCompleteListener {

    void onTaskCompleted(ArrayList<Article> articles);

    void onError();
}
