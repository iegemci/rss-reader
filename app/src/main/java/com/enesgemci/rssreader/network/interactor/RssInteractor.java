package com.enesgemci.rssreader.network.interactor;

import android.os.Handler;
import android.os.Looper;

import com.enesgemci.rssreader.di.GraphFactory;
import com.enesgemci.rssreader.network.MExecutor;
import com.enesgemci.rssreader.network.client.RssNetworkClient;
import com.enesgemci.rssreader.rss.Article;
import com.enesgemci.rssreader.rss.XmlParser;

import java.util.ArrayList;

public class RssInteractor extends Interactor {

    private Handler mainHandler;

    public RssInteractor() {
        mainHandler = new Handler(Looper.getMainLooper());
    }

    public void getArticles(final OnTaskCompleteListener listener) {
        MExecutor.execute(() -> {
            RssNetworkClient client = GraphFactory.getInstance().provideNetworkClient();
            String response = client.connect();
            ArrayList<Article> articles = new ArrayList<>();

            try {
                articles = XmlParser.parse(response);
            } catch (Exception e) {
                // ignore for now
            }

            final ArrayList<Article> finalArticles = articles;

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
