package com.enesgemci.rssreader.ui.article;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.enesgemci.rssreader.R;
import com.enesgemci.rssreader.base.BaseFragment;
import com.enesgemci.rssreader.network.client.ImageDownloaderClient;
import com.enesgemci.rssreader.rss.Article;
import com.enesgemci.rssreader.ui.article.adapter.ArticlesAdapter;

import java.util.ArrayList;

public class ArticleFragment extends BaseFragment<ArticleView, ArticlePresenter> implements ArticleView {

    public static String TAG = "ArticleFragment";
    private static String ARTICLES = "ARTICLES";

    private RecyclerView articlesRecyclerView;
    private ArticlesAdapter adapter;
    private ArrayList<Article> articles;

    public static ArticleFragment newInstance() {
        return new ArticleFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if (articles != null) {
            outState.putParcelableArrayList(ARTICLES, articles);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        articlesRecyclerView = findViewById(R.id.articlesRecyclerView);
        articlesRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        adapter = new ArticlesAdapter(
                requireContext(),
                article -> getPresenter().onArticleClicked(article),
                new ImageDownloaderClient()
        );
        articlesRecyclerView.setAdapter(adapter);

        if (savedInstanceState == null) {
            getPresenter().getArticles();
        } else {
            setArticles(savedInstanceState.getParcelableArrayList(ARTICLES));
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_articles;
    }

    @NonNull
    @Override
    protected ArticlePresenter createPresenter() {
        return new ArticlePresenter();
    }

    @Override
    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;

        if (adapter != null) {
            adapter.setArticles(articles);
        }
    }

    @Override
    public void openArticle(String link) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(link));
        startActivity(i);
    }

    @Override
    public void onResponseError() {
        Toast.makeText(requireContext(), getString(R.string.response_error), Toast.LENGTH_LONG).show();
    }
}
