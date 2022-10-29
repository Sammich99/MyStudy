package com.example.mystudy;

public class SearchData {
    private String title, link, snippet;

    public SearchData(String title, String link, String snippet) {
        this.title = title;
        this.link = link;
        this.snippet = snippet;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }
}
