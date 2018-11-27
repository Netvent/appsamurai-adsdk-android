package com.appsamurai.adsdk.sample.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View

import com.appsamurai.ads.common.AdRequest
import com.appsamurai.ads.common.MobileAds
import com.appsamurai.ads.reward.RewardedVideoAd
import com.appsamurai.ads.reward.RewardedVideoAdListener

class VideoAdActivity : AppCompatActivity() {

    // Add an RewardedVideoAd field
    private var mRewardedVideoAd: RewardedVideoAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_video_ad)

        // Initialize the SDK with App Unit ID
        MobileAds.initialize(applicationContext, "gJIwJ-T0Kst86Mw3JIk-1A")

        // Create an RewardedVideoAd instance and set Ad Unit ID
        mRewardedVideoAd = RewardedVideoAd(this)
        val adUnitId = "nnrgOQwJmrRCuppxWA0Q_A"
        mRewardedVideoAd!!.adUnitId = adUnitId

        // set an AdListener in order to track the state of the ad
        mRewardedVideoAd!!.setRewardedVideoAdListener(object : RewardedVideoAdListener() {

            // Code to be executed when an ad finishes loading.
            override fun onRewardedVideoAdLoaded() {
                Log.d(Utils.LOGTAG, "Ad Loaded: $adUnitId")
            }

            // Code to be executed when an ad request fails.
            override fun onRewardedVideoAdFailedToLoad(errorCode: Int) {
                Log.d(Utils.LOGTAG, "Ad Failed To Load: $adUnitId $errorCode")
            }

            // Code to be executed when the ad is displayed.
            override fun onRewardedVideoAdOpened() {
                Log.d(Utils.LOGTAG, "Ad Opened: $adUnitId")
            }

            // Code to be executed when the video is started.
            override fun onRewardedVideoStarted() {
                Log.d(Utils.LOGTAG, "Video Started")
            }

            // Code to be executed when the user has gained the reward.
            override fun onRewarded() {
                Log.d(Utils.LOGTAG, "Rewarded")
            }

            // Code to be executed when the user has left the app.
            override fun onRewardedVideoAdLeftApplication() {
                Log.d(Utils.LOGTAG, "Ad Left Application: $adUnitId")
            }

            // Code to be executed when the video is completed.
            override fun onRewardedVideoCompleted() {
                Log.d(Utils.LOGTAG, "Video Completed")
            }

            // Code to be executed when when the ad is closed.
            override fun onRewardedVideoAdClosed() {
                Log.d(Utils.LOGTAG, "Ad Closed")
            }

        })
    }

    fun loadVideoAd(view: View) {
        // Create an AdRequest and load ad with this AdRequest
        val adRequest = AdRequest.Builder().build()
        mRewardedVideoAd!!.loadAd(adRequest)
    }

    fun showVideoAd(view: View) {
        // Before showing interstitial check if it is loaded
        if (mRewardedVideoAd!!.isLoaded) {
            mRewardedVideoAd!!.show()
        }
    }
}
