package com.geekhub.lakhmaniuk.rssreader.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geekhub.lakhmaniuk.rssreader.R;
import com.geekhub.lakhmaniuk.rssreader.WebViewActivity;
import com.geekhub.lakhmaniuk.rssreader.asynctask.ImageDownloaderTask;
import com.geekhub.lakhmaniuk.rssreader.model.FeedItem;

/**
 * Created by Miho on 01.12.2014.
 */
public class FeedDetailsFragment extends Fragment {
    private FeedItem feed;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_feed_details,
                container, false);
        feed = (FeedItem) getActivity().getIntent().getSerializableExtra("feed");

        if (null != feed) {
            ImageView thumb = (ImageView) view.findViewById(R.id.featuredImg);
            new ImageDownloaderTask(thumb).execute(feed.getAttachmentUrl());

            TextView title = (TextView) view.findViewById(R.id.title);
            title.setText(feed.getTitle());

            TextView htmlTextView = (TextView) view.findViewById(R.id.content);
            htmlTextView.setText(Html.fromHtml(feed.getContent(), null, null));
        }
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_share:
                shareContent();
                return true;
            case R.id.menu_view:
                Intent intent;
                intent = new Intent(FeedDetailsFragment.this, WebViewFragment.class);
                intent.putExtra("url", feed.getUrl());
                startActivity(intent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void shareContent() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, feed.getTitle() + "\n" + feed.getUrl());
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "Share using"));

    }

}
