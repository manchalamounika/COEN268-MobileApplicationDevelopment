package com.mmanchala.coen268.fragmentswiki;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.webkit.WebView;
import android.widget.ListView;


public class ChildActivity extends FragmentActivity implements ListFragment.OnChildItemClickListener {

    ListView listView;
    boolean dualMode = false;
    public static String currentItem;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_view);
        listView = findViewById(R.id.childListView);
        Bundle bundle = new Bundle();
        bundle.putInt("clickedItem", getIntent().getExtras().getInt("clickedItem"));

        ListFragment listFragment = new ListFragment();
        listFragment.setArguments(bundle);

        if (savedInstanceState == null) {
            android.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.childDetail, listFragment);
            fragmentTransaction.commit();
        }

        if (findViewById(R.id.webDetail) != null) {
            dualMode = true;
            getFragmentManager().popBackStack();
            DetailWebFragment webFragment = (DetailWebFragment) getFragmentManager().findFragmentById(R.id.webDetail);
            if (webFragment == null) {
                webFragment = new DetailWebFragment();
                android.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.webDetail, webFragment);
                fragmentTransaction.commit();
            }
        }
    }

    @Override
    public void onItemSelected(String url) {
        if (dualMode) {
            DetailWebFragment webFragment = (DetailWebFragment) getFragmentManager().findFragmentById(R.id.webDetail);
            WebView webView = webFragment.getView().findViewById(R.id.webView);
            webFragment.updateWebView(url, webView);
        } else {
            DetailWebFragment webFragment = new DetailWebFragment();
            webFragment.setUrl(url);
            android.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.childDetail, webFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}