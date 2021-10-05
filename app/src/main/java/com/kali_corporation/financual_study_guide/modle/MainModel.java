package com.kali_corporation.financual_study_guide.modle;

import java.util.ArrayList;

public class MainModel {

    private String status;
    private String totalResult;
    private ArrayList<NewsModel> articles;

    public MainModel(String status, String totalResult, ArrayList<NewsModel> articles) {
        this.status = status;
        this.totalResult = totalResult;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(String totalResult) {
        this.totalResult = totalResult;
    }

    public ArrayList<NewsModel> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<NewsModel> articles) {
        this.articles = articles;
    }
}
