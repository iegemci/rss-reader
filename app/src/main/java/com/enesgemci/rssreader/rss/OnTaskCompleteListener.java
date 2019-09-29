package com.enesgemci.rssreader.rss;

import java.util.List;

public interface OnTaskCompleteListener {

    void onTaskCompleted(List<Article> articles);

    void onError();
}
