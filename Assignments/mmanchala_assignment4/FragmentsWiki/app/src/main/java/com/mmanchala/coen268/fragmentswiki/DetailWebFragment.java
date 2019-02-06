package com.mmanchala.coen268.fragmentswiki;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DetailWebFragment extends Fragment  {

    String url;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        WebView clientWebView = getView().findViewById(R.id.webView);
        clientWebView.setWebViewClient(new WebViewClient());

        if(ChildActivity.currentItem != null)
            clientWebView.loadUrl(ChildActivity.currentItem);
        clientWebView.loadUrl(this.url);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.web_view, container, false);
        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void updateWebView(String s, WebView webView) {
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(s);
    }

}
