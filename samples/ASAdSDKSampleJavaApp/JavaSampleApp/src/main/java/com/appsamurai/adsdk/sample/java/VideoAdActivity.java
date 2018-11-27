package com.appsamurai.adsdk.sample.java;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.appsamurai.ads.common.AdRequest;
import com.appsamurai.ads.common.MobileAds;
import com.appsamurai.ads.reward.RewardedVideoAd;
import com.appsamurai.ads.reward.RewardedVideoAdListener;

public class VideoAdActivity extends AppCompatActivity {

    // Add an RewardedVideoAd field
    private RewardedVideoAd mRewardedVideoAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_video_ad);

        // Initialize the SDK with App Unit ID
        MobileAds.initialize(getApplicationContext(), "gJIwJ-T0Kst86Mw3JIk-1A");

        // Create an RewardedVideoAd instance and set Ad Unit ID
        mRewardedVideoAd =  new RewardedVideoAd(this);
        final String adUnitId = "nnrgOQwJmrRCuppxWA0Q_A";
        mRewardedVideoAd.setAdUnitId(adUnitId);

        // set an AdListener in order to track the state of the ad
        mRewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {

            // Code to be executed when an ad finishes loading.
            @Override
            public void onRewardedVideoAdLoaded() {
                Log.d(Utils.LOGTAG, "Ad Loaded: " + adUnitId);
            }

            // Code to be executed when an ad request fails.
            @Override
            public void onRewardedVideoAdFailedToLoad(int errorCode) {
                Log.d(Utils.LOGTAG, "Ad Failed To Load: " + adUnitId + " " + errorCode);
            }

            // Code to be executed when the ad is displayed.
            @Override
            public void onRewardedVideoAdOpened() {
                Log.d(Utils.LOGTAG, "Ad Opened: " + adUnitId);
            }

            // Code to be executed when the video is started.
            @Override
            public void onRewardedVideoStarted() {
                Log.d(Utils.LOGTAG, "Video Started");
            }

            // Code to be executed when the user has gained the reward.
            @Override
            public void onRewarded() {
                Log.d(Utils.LOGTAG, "Rewarded");
            }

            // Code to be executed when the user has left the app.
            @Override
            public void onRewardedVideoAdLeftApplication() {
                Log.d(Utils.LOGTAG, "Ad Left Application: " + adUnitId);
            }

            // Code to be executed when the video is completed.
            @Override
            public void onRewardedVideoCompleted() {
                Log.d(Utils.LOGTAG, "Video Completed");
            }

            // Code to be executed when when the ad is closed.
            @Override
            public void onRewardedVideoAdClosed() {
                Log.d(Utils.LOGTAG, "Ad Closed");
            }

        });
    }

    public void loadVideoAd(View view) {
        // Create an AdRequest and load ad with this AdRequest
        AdRequest adRequest = new AdRequest.Builder().build();
        mRewardedVideoAd.loadAd(adRequest);
    }

    public void showVideoAd(View view) {
        // Before showing interstitial check if it is loaded
        if ( mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.show();
        }
    }
}
