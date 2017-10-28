package com.example.koira.homework_5;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by koira on 10/26/2017.
 */

public class Result implements Serializable{
    private String title;
    private String summary;
    private String releaseDate;
    private String updatedDate;
    private String smallImage;
    private String largeImage;

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public Date getReleaseDate() throws ParseException {
        return new SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss").parse(this.releaseDate.trim());
    }

    public Date getUpdatedDate() throws ParseException {
        return new SimpleDateFormat("yyyy-mm-dd'T'hh:mm:ss").parse(this.updatedDate.trim());
    }

    public String getSmallImage() {
        return smallImage;
    }

    public String getLargeImage() {
        return largeImage;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    public void setLargeImage(String largeImage) {
        this.largeImage = largeImage;
    }

    @Override
    public String toString() {
        return "Result{" +
                "title='" + title;
    }
}
