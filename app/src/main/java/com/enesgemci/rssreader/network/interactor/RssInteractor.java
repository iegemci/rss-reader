package com.enesgemci.rssreader.network.interactor;

import android.os.Handler;
import android.os.Looper;

import com.enesgemci.rssreader.di.GraphFactory;
import com.enesgemci.rssreader.network.client.RssNetworkClient;
import com.enesgemci.rssreader.rss.Article;
import com.enesgemci.rssreader.rss.OnTaskCompleteListener;
import com.enesgemci.rssreader.rss.XmlParser;

import java.util.ArrayList;
import java.util.List;

public class RssInteractor extends Interactor {

    private Handler mainHandler;

    public RssInteractor() {
        mainHandler = new Handler(Looper.getMainLooper());
    }

    public void getArticles(final OnTaskCompleteListener listener) {
        executor.execute(() -> {
            RssNetworkClient client = GraphFactory.getInstance().provideNetworkClient();
            String response = client.connect();
            List<Article> articles = new ArrayList<>();

            try {
                articles = XmlParser.parse(response);
            } catch (Exception e) {
                // ignore for now
            }

            final List<Article> finalArticles = articles;

            mainHandler.post(() -> {
                if (finalArticles.isEmpty()) {
                    listener.onError();
                } else {
                    listener.onTaskCompleted(finalArticles);
                }
            });
        });
    }
}
