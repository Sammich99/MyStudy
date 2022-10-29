package com.example.mystudy;

import java.util.List;

public class Search {
    List<SearchData> organic_results;

    public Search(List<SearchData> organic_results) {
        this.organic_results = organic_results;
    }

    public List<SearchData> getOrganic_results() {
        return organic_results;
    }

    public void setOrganic_results(List<SearchData> organic_results) {
        this.organic_results = organic_results;
    }
}
