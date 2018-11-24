package com.appsamurai.adsdk.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.appsamurai.ads.common.AdListener;
import com.appsamurai.ads.common.AdRequest;
import com.appsamurai.ads.common.MobileAds;
import com.appsamurai.ads.interstitial.InterstitialAd;

import java.util.logging.Level;

public class InterstitialAdActivity extends AppCompatActivity {

    // Add an InterstitialAd field
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial_ad);

        // Define log level
        MobileAds.setLogLevel(Level.ALL);

        // Initialize the SDK with App Unit ID
        MobileAds.initialize(getApplicationContext(), "gJIwJ-T0Kst86Mw3JIk-1A");

        // Create an InterstitialAd instance and set Ad Unit ID
        mInterstitialAd = new InterstitialAd(this);
        final String adUnitId = "nnrgOQ8JmbRCupYRQyNQwg";
        mInterstitialAd.setAdUnitId(adUnitId);

        // Set an AdListener in order to track the state of the ad
        mInterstitialAd.setAdListener(new AdListener() {

            // Code to be executed when an ad finishes loading.
            @Override
            public void onAdLoaded() {
                Log.d(Utils.LOGTAG, "Ad Loaded: " + adUnitId);
            }

            // Code to be executed when an ad request fails.
            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.d(Utils.LOGTAG, "Ad Failed To Load: " + adUnitId + " " + errorCode);
            }

            // Code to be executed when an ad opens an overlay that covers the screen.
            @Override
            public void onAdOpened() {
                Log.d(Utils.LOGTAG, "Ad Opened: " + adUnitId);
            }

            // Code to be executed when when the user is about to return to the app after tapping on an ad.
            @Override
            public void onAdClosed() {
                Log.d(Utils.LOGTAG, "Ad Closed: " + adUnitId);
            }

            // Code to be executed when the user has left the app.
            @Override
            public void onAdLeftApplication() {
                Log.d(Utils.LOGTAG, "Ad Left Application: " + adUnitId);
            }
        });
    }

    public void loadInterstitial(View view) {
        // Create an AdRequest and load ad with this AdRequest
        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
    }

    public void showInterstitial(View view) {
        // Before showing interstitial check if it is loaded
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d(Utils.LOGTAG, "Interstitial was not ready to be shown.");
        }
    }
}
