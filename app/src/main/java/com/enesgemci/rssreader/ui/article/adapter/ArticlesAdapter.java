package com.enesgemci.rssreader.ui.article.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.enesgemci.rssreader.R;
import com.enesgemci.rssreader.network.client.ImageDownloaderClient;
import com.enesgemci.rssreader.rss.Article;

import java.util.List;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {

    private final LayoutInflater layoutInflater;
    private List<Article> articles;
    private final ItemClickListener itemClickListener;
    private final ImageDownloaderClient downloaderClient;
    private Handler mainHandler;

    public ArticlesAdapter(Context context, ItemClickListener itemClickListener, ImageDownloaderClient downloaderClient) {
        this.layoutInflater = LayoutInflater.from(context);
        this.itemClickListener = itemClickListener;
        this.downloaderClient = downloaderClient;
        this.mainHandler = new Handler(Looper.getMainLooper());
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
        notifyDataSetChanged();
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

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.rssImage);
            title = itemView.findViewById(R.id.rssTitle);
        }

        void bind(Article article) {
            title.setText(article.getTitle());
            downloaderClient.connect(
                    article.getImage(),
                    mainHandler,
                    image
            );

            title.setOnClickListener(v -> {
                if (itemClickListener != null) {
                    itemClickListener.onItemClicked(article);
                }
            });
        }
    }
}
