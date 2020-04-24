package com.example.mhmsbmrapp.DashboardBmr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;

import com.example.mhmsbmrapp.R;

public class About_kmhms extends Fragment {

    public About_kmhms() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.about_kmhms_fragment, container, false);

        WebView webView = (WebView) view.findViewById(R.id.webview);


        // Enable Javascript
        webView.getSettings().setJavaScriptEnabled(true);
        // Force links and redirects to open in the WebView instead of in a browser
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.google.com");

        return view;


    }
}
