package com.enesgemci.rssreader.rss;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Article implements Parcelable {

    private String guid;
    private String title;
    private String author;
    private String link;
    private String pubDate;
    private String description;
    private String content;
    private String image;
    private ArrayList<String> categories = new ArrayList<>();

    public Article() {
    }

    protected Article(Parcel in) {
        guid = in.readString();
        title = in.readString();
        author = in.readString();
        link = in.readString();
        pubDate = in.readString();
        description = in.readString();
        content = in.readString();
        image = in.readString();
        categories = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(guid);
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(link);
        dest.writeString(pubDate);
        dest.writeString(description);
        dest.writeString(content);
        dest.writeString(image);
        dest.writeStringList(categories);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void addCategory(String category) {
        categories.add(category);
    }
}
