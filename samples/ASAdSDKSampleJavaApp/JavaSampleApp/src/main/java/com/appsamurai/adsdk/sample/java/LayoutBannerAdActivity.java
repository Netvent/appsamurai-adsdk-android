package com.appsamurai.adsdk.sample.java;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.appsamurai.ads.banner.AdView;
import com.appsamurai.ads.common.AdListener;
import com.appsamurai.ads.common.AdRequest;
import com.appsamurai.ads.common.AdSize;
import com.appsamurai.ads.common.MobileAds;

import java.util.logging.Level;

public class LayoutBannerAdActivity extends AppCompatActivity {

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_banner_ad);

        MobileAds.setLogLevel(Level.ALL);
        // initialize Mobile Ads
        MobileAds.initialize(getApplicationContext(), "gJIwJ-T0Kst86Mw3JIk-1A");

        // set ad view
        mAdView = findViewById(R.id.adView);

        // create an ad request and load ad with this ad request
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // set an AdListener in order to track the state of the ad
        mAdView.setAdListener(new AdListener() {
            // Code to be executed when an ad finishes loading.
            @Override
            public void onAdLoaded() {
                Log.d(Utils.LOGTAG, "Ad Loaded: " + mAdView.getAdUnitId());
            }

            // Code to be executed when an ad request fails.
            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.d(Utils.LOGTAG, "Ad Failed To Load: " + mAdView.getAdUnitId() + " " + errorCode);
            }

            // Code to be executed when an ad opens an overlay that covers the screen.
            @Override
            public void onAdOpened() {
                Log.d(Utils.LOGTAG, "Ad Opened: " + mAdView.getAdUnitId());
            }

            // Code to be executed when the user has left the app.
            @Override
            public void onAdLeftApplication() {
                Log.d(Utils.LOGTAG, "Ad Left Application: " + mAdView.getAdUnitId());
            }

            // Code to be executed when when the user is about to return to the app after tapping on an ad.
            @Override
            public void onAdClosed() {
                Log.d(Utils.LOGTAG, "Ad Closed: " + mAdView.getAdUnitId());
            }
        });
    }
}
