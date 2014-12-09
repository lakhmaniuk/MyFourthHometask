package com.geekhub.lakhmaniuk.rssreader.fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.app.Fragment;
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
import com.geekhub.lakhmaniuk.rssreader.asynctask.ImageDownloaderTask;
import com.geekhub.lakhmaniuk.rssreader.model.FeedItem;

/**
 * Created by Miho on 01.12.2014.
 */
public class FeedDetailsFragment extends Fragment {
    private FeedItem feed;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed_details,
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

}
