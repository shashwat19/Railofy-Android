package com.simplicitydev.railofy;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class PNR_Status extends Fragment {


    public PNR_Status() {
        // Required empty public constructor
    }

    WebView wv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_pnr__status, container, false);

        wv= (WebView) view.findViewById(R.id.pnr);

        wv.getSettings().setJavaScriptEnabled(true);

        wv.setWebViewClient(new WebViewClient());
        wv.loadUrl("http://www.indianrail.gov.in/enquiry/PNR/PnrEnquiry.html?locale=en");

        return view;
    }

}
