package com.geekhub.lakhmaniuk.rssreader.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.geekhub.lakhmaniuk.rssreader.R;
import com.geekhub.lakhmaniuk.rssreader.asynctask.ImageDownloaderTask;
import com.geekhub.lakhmaniuk.rssreader.fragment.FeedListFragment;
import com.geekhub.lakhmaniuk.rssreader.model.FeedItem;

public class FeedDetailsActivity extends Activity {

    private FeedItem feed;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_feed_details);

        FeedListFragment fragment = new FeedListFragment();
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().add(R.id.details_fragment, fragment).commit();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menu_share:
//                shareContent();
//                return true;
//            case R.id.menu_view:
//                Intent intent = new Intent(FeedDetailsActivity.this, WebViewActivity.class);
//                intent.putExtra("url", feed.getUrl());
//                startActivity(intent);
//
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//
//    private void shareContent() {
//        Intent sendIntent = new Intent();
//        sendIntent.setAction(Intent.ACTION_SEND);
//        sendIntent.putExtra(Intent.EXTRA_TEXT, feed.getTitle() + "\n" + feed.getUrl());
//        sendIntent.setType("text/plain");
//        startActivity(Intent.createChooser(sendIntent, "Share using"));
//
//    }
}