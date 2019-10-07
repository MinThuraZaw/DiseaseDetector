package com.example.diseasedetector.model;

import com.google.gson.annotations.SerializedName;

public class NewsModel {

    private String totalResults;

    private Articles[] articles;

    private String status;

    public String getTotalResults ()
    {
        return totalResults;
    }

    public void setTotalResults (String totalResults)
    {
        this.totalResults = totalResults;
    }

    public Articles[] getArticles ()
    {
        return articles;
    }

    public void setArticles (Articles[] articles)
    {
        this.articles = articles;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }
}
