package com.example.koira.inclass06;

/**
 * Created by koira on 11/22/2017.
 */

public class Article {
    String author, title, url, urlToImage, publishedAt;

    @Override
    public String toString() {
        return "Article{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                '}';
    }
}
