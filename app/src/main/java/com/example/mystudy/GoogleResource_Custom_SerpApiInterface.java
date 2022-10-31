package com.example.mystudy;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleResource_Custom_SerpApiInterface {

    @GET("/search?&num=10&api_key=cc31bb030a7dc9dc72dbd78175f8450d029352cf51540552dce5e6f3fee71048")
    Call<Search> fetchSearches(@Query("q") String searchText);
}
