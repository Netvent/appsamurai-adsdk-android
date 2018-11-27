package com.appsamurai.adsdk.sample.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.RelativeLayout

import com.appsamurai.ads.banner.AdView
import com.appsamurai.ads.common.AdListener
import com.appsamurai.ads.common.AdRequest
import com.appsamurai.ads.common.MobileAds

class ProgrammaticBannerAdActivity : AppCompatActivity() {

    private var mAdView: AdView? = null
    private var adContainer: RelativeLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_programmatic_banner_ad)

        // initialize Mobile Ads
        MobileAds.initialize(applicationContext, "gJIwJ-T0Kst86Mw3JIk-1A")

        // get the container layout
        adContainer = findViewById(R.id.adContainer)

        // create an adview and set ad unit id
        mAdView = AdView(this)
        mAdView!!.adUnitId = "nnrgOQ4JmLRCuphTYTkRvg"

        // create a layout params and add ad view to the container view with this layout params
        val params = RelativeLayout.LayoutParams(Utils.dpToPx(320), Utils.dpToPx(50))
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)
        params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE)
        adContainer!!.addView(mAdView, params)

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
