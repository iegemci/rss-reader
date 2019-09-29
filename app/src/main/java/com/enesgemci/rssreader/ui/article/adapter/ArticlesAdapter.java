package com.enesgemci.rssreader.ui.article.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.enesgemci.rssreader.R;
import com.enesgemci.rssreader.rss.Article;

import java.util.List;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {

    private final LayoutInflater layoutInflater;
    private final List<Article> articles;

    public ArticlesAdapter(Context context, List<Article> articles) {
        layoutInflater = LayoutInflater.from(context);
        this.articles = articles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_article, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(articles.get(position));
    }

    @Override
    public int getItemCount() {
        return articles != null ? articles.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.rssImage);
            title = itemView.findViewById(R.id.rssTitle);
        }

        void bind(Article article) {
            title.setText(article.getTitle());
        }
    }
}
