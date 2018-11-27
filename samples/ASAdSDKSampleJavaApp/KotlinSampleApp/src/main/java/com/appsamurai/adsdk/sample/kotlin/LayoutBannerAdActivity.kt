package com.appsamurai.adsdk.sample.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

import com.appsamurai.ads.banner.AdView
import com.appsamurai.ads.common.AdListener
import com.appsamurai.ads.common.AdRequest
import com.appsamurai.ads.common.MobileAds

import java.util.logging.Level

class LayoutBannerAdActivity : AppCompatActivity() {

    private var mAdView: AdView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_banner_ad)

        MobileAds.setLogLevel(Level.ALL)
        // initialize Mobile Ads
        MobileAds.initialize(applicationContext, "gJIwJ-T0Kst86Mw3JIk-1A")

        // set ad view
        mAdView = findViewById(R.id.adView)

        // create an ad request and load ad with this ad request
        val adRequest = AdRequest.Builder().build()
        mAdView!!.loadAd(adRequest)

        // set an AdListener in order to track the state of the ad
        mAdView!!.adListener = object : AdListener() {
            // Code to be executed when an ad finishes loading.
            override fun onAdLoaded() {
                Log.d(Utils.LOGTAG, "Ad Loaded: " + mAdView!!.adUnitId)
            }

            // Code to be executed when an ad request fails.
            override fun onAdFailedToLoad(errorCode: Int) {
                Log.d(Utils.LOGTAG, "Ad Failed To Load: " + mAdView!!.adUnitId + " " + errorCode)
            }

            // Code to be executed when an ad opens an overlay that covers the screen.
            override fun onAdOpened() {
                Log.d(Utils.LOGTAG, "Ad Opened: " + mAdView!!.adUnitId)
            }

            // Code to be executed when the user has left the app.
            override fun onAdLeftApplication() {
                Log.d(Utils.LOGTAG, "Ad Left Application: " + mAdView!!.adUnitId)
            }

            // Code to be executed when when the user is about to return to the app after tapping on an ad.
            override fun onAdClosed() {
                Log.d(Utils.LOGTAG, "Ad Closed: " + mAdView!!.adUnitId)
            }
        }
    }
}
