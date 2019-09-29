package com.enesgemci.rssreader.network.interactor;

import android.os.AsyncTask;

import com.enesgemci.rssreader.di.GraphFactory;
import com.enesgemci.rssreader.network.client.RssNetworkClient;
import com.enesgemci.rssreader.rss.Article;
import com.enesgemci.rssreader.rss.OnTaskCompleteListener;
import com.enesgemci.rssreader.rss.XmlParser;

import java.util.ArrayList;
import java.util.List;

public class RssInteractor extends Interactor {

    private String url;

    public RssInteractor(String url) {
        this.url = url;
    }

    public void getArticles(final OnTaskCompleteListener listener) {
        new AsyncTask<Void, List<Article>, List<Article>>() {
            @Override
            protected List<Article> doInBackground(Void... voids) {
                RssNetworkClient client = GraphFactory.getInstance().provideNetworkClient();
                String response = client.connect();
                List<Article> articles = new ArrayList<>();

                try {
                    articles = XmlParser.parse(response);
                } catch (Exception e) {
                    return articles;
                }

                return articles;
            }

            @Override
            protected void onPostExecute(List<Article> articles) {
                if (articles.isEmpty()) {
                    listener.onError();
                } else {
                    listener.onTaskCompleted(articles);
                }
            }
        }.execute();
    }
}
