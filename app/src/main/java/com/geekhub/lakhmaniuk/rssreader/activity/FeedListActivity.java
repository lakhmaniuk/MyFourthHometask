package com.geekhub.lakhmaniuk.rssreader.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.geekhub.lakhmaniuk.rssreader.R;
import com.geekhub.lakhmaniuk.rssreader.adapter.CustomListAdapter;
import com.geekhub.lakhmaniuk.rssreader.fragment.FeedDetailsFragment;
import com.geekhub.lakhmaniuk.rssreader.fragment.FeedListFragment;
import com.geekhub.lakhmaniuk.rssreader.model.FeedItem;

public class FeedListActivity extends Activity implements FeedListFragment.EventOnListFragment {

    private ArrayList<FeedItem> feedList = null;
    private ProgressBar progressbar = null;
    private ListView feedListView = null;
    private FragmentManager fm;
    FeedListFragment listFragment;
    FeedDetailsFragment detailsFragment;
    int position = 0;
    boolean withContent = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        progressbar = (ProgressBar) findViewById(R.id.progressBar);
        String url = "http://javatechig.com/api/get_category_posts/?dev=1&slug=android";


        withContent = (findViewById(R.id.details_fragment) != null);
        fm = getFragmentManager();

        //Show list of entries
        listFragment = (FeedListFragment) fm.findFragmentById(R.id.list_fragment);
        if (listFragment == null) {
            listFragment = new FeedListFragment();
            fm.beginTransaction().add(R.id.list_fragment, listFragment).commit();
        }
        listFragment.setEvent(this);

        //Show content(detail) fragment if possible
        if(withContent){
            detailsFragment = new FeedDetailsFragment();
            fm.beginTransaction().replace(R.id.details_fragment, detailsFragment).commit();
        }

         else {
            //TODO show "No connections"
        }

    }

    boolean isLarge() {
        return (getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    @Override
    public void eventClickOnListFragment(int position) {
        detailsFragment = (FeedDetailsFragment) fm.findFragmentById(R.id.details_fragment);
        if (getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE && isLarge()){
            detailsFragment = new FeedDetailsFragment();
            fm.beginTransaction().replace(R.id.details_fragment, detailsFragment).commit();
        }

    }

}