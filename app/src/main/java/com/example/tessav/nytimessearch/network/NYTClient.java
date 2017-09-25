package com.example.tessav.nytimessearch.network;

import com.loopj.android.http.AsyncHttpClient;

/**
 * Created by tessavoon on 9/19/17.
 */

public class NYTClient {
    private final String API_KEY = "87c12628554a4caca74a408561b5b835";
    private final String API_BASE_URL = "https://api.nytimes.com/svc/search/v2/articlesearch.json";
    private AsyncHttpClient client;

    public NYTClient() {
         this.client = new AsyncHttpClient();
    }

    private String getApiUrl(String relativeUrl) {
        return API_BASE_URL + relativeUrl;
    }

}
