package com.sharma.deepak.newsapp.model;

/**
 * Created by deepaks on 7/7/2017.
 */

public class Article {
    String sectionName, webUrl, webTitle, date;

    public Article(String sectionName, String webUrl, String webTitle, String date) {
        this.sectionName = sectionName;
        this.webUrl = webUrl;
        this.webTitle = webTitle;
        this.date = date;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
