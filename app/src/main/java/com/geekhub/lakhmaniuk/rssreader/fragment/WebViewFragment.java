package com.geekhub.lakhmaniuk.rssreader.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.geekhub.lakhmaniuk.rssreader.R;

/**
 * Created by Miho on 02.12.2014.
 */
public class WebViewFragment extends Fragment {
    private WebView webView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_webview,
                container, false);
        webView = (WebView) view.findViewById(R.id.webView);
        webView.setWebViewClient(new MyWebViewClient());

        Bundle bundle = getActivity().getIntent().getExtras();
        String url = bundle.getString("url");

        if (null != url) {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(url);
        }
        return view;
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
