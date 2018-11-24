package com.appsamurai.adsdk.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.appsamurai.ads.common.AdRequest;
import com.appsamurai.ads.common.MobileAds;
import com.appsamurai.ads.reward.RewardedVideoAd;
import com.appsamurai.ads.reward.RewardedVideoAdListener;

public class VideoAdActivity extends AppCompatActivity {

    private RewardedVideoAd mRewardedVideoAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_video_ad);

        // Initialize the SDK with App Unit ID
        MobileAds.initialize(getApplicationContext(), "gJIwJ-T0Kst86Mw3JIk-1A");

        // get singleton rewarded video instance
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);

        // set an AdListener in order to track the state of the ad
        mRewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {
                Log.d(Utils.LOGTAG, "onRewardedVideoAdLoaded");
            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int errorCode) {
                Log.d(Utils.LOGTAG, "onRewardedVideoAdFailedToLoad: " + errorCode);
            }

            @Override
            public void onRewardedVideoAdOpened() {
                Log.d(Utils.LOGTAG, "onRewardedVideoAdOpened");
            }

            @Override
            public void onRewardedVideoStarted() {
                Log.d(Utils.LOGTAG, "onRewardedVideoStarted");
            }

            @Override
            public void onRewarded() {
                Log.d(Utils.LOGTAG, "onRewarded");
            }

            @Override
            public void onRewardedVideoAdLeftApplication() {
                Log.d(Utils.LOGTAG, "onRewardedVideoAdLeftApplication");
            }

            @Override
            public void onRewardedVideoCompleted() {
                Log.d(Utils.LOGTAG, "onRewardedVideoCompleted");
            }

            @Override
            public void onRewardedVideoAdClosed() {
                Log.d(Utils.LOGTAG, "onRewardedVideoAdClosed");
            }

        });
    }

    public void loadVideoAd(View view) {
        // Create an AdRequest and load ad with this AdRequest
        AdRequest adRequest = new AdRequest.Builder().build();
        mRewardedVideoAd.loadAd("nnrgOQwJmrRCuppxWA0Q_A", adRequest);
    }

    public void showVideoAd(View view) {
        // Before showing interstitial check if it is loaded
        if ( mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.show();
        }
    }
}
