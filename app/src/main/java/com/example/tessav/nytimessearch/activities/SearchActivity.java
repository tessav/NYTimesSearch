package com.example.tessav.nytimessearch.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.example.tessav.nytimessearch.R;
import com.example.tessav.nytimessearch.adapters.ArticleArrayAdapter;
import com.example.tessav.nytimessearch.fragments.FilterFragment;
import com.example.tessav.nytimessearch.models.Article;
import com.example.tessav.nytimessearch.network.NYTClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class SearchActivity extends AppCompatActivity {
    @BindView(R.id.etQuery) EditText etQuery;
    @BindView(R.id.gvResults) GridView gvResults;
    @BindView(R.id.btnSearch) Button btnSearch;
    @BindView(R.id.toolbar) Toolbar toolbar;

    ArrayList<Article> articles;
    ArticleArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        articles = new ArrayList<>();
        adapter = new ArticleArrayAdapter(this, articles);
        gvResults.setAdapter(adapter);

        // hook up listener for grid click
        gvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ArticleActivity.class);
                Article article = articles.get(i);
                intent.putExtra("article", article);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_filter) {
            showFilterDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onArticleSearch(View view) {
        System.out.println("searching articles...");
        String query = etQuery.getText().toString();
        NYTClient client = new NYTClient();
        client.search(this, query, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray articleJsonResults = null;
                try {
                    adapter.clear();
                    articleJsonResults = response.getJSONObject("response").getJSONArray("docs");
                    System.out.println("got results");
                    adapter.addAll(Article.fromJSONArray(articleJsonResults));
                } catch(JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

            }
        });
    }

    private void showFilterDialog() {
        FragmentManager fm = getSupportFragmentManager();
        FilterFragment filterFragment = FilterFragment.newInstance("Some Title");
        filterFragment.show(fm, "fragment_filter");
    }

}
