package com.example.tessav.nytimessearch.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

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

    public void search(Context context, String query, int page, JsonHttpResponseHandler handler) {
        RequestParams params = buildRequest(context, query, page);
        client.get(API_BASE_URL, params, handler);
    }

    private RequestParams buildRequest(Context context, String query, int page) {
        SharedPreferences filterSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        int beginYear = filterSharedPreferences.getInt("beginYear", 2017);
        int beginMonth = filterSharedPreferences.getInt("beginMonth", 01);
        int beginDay = filterSharedPreferences.getInt("beginDay", 01);
        String sbYear = String.valueOf(beginYear);
        boolean cbArts = filterSharedPreferences.getBoolean("cbArts", false);
        boolean cbFashion = filterSharedPreferences.getBoolean("cbFashion", false);
        boolean cbSports = filterSharedPreferences.getBoolean("cbSports", false);
        String sortOrder = filterSharedPreferences.getString("sortOrder", "newest");
        String sbMonth = (beginMonth < 10) ? "0" + String.valueOf(beginMonth) : String.valueOf(beginMonth);
        String sbDay = (beginDay < 10) ? "0" + String.valueOf(beginDay) : String.valueOf(beginDay);
        String newsDesk = "";
        if (cbArts) {
            newsDesk += "\"Arts\" ";
        }
        if (cbFashion) {
            newsDesk += "\"Fashion & Style\" ";
        }
        if (cbSports) {
            newsDesk += "\"Sports\" ";
        }
        if (!newsDesk.isEmpty()) {
            newsDesk = "news_desk:(" + newsDesk.trim() + ")";
        }
        RequestParams params = new RequestParams();
        params.put("api-key",API_KEY);
        params.put("page", page);
        params.put("query", query);
        params.put("begin_date", sbYear + sbMonth + sbDay);
        params.put("fq", newsDesk);
        params.put("sort", sortOrder);
        return params;
    }

}
