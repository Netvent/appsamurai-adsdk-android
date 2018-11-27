package com.appsamurai.adsdk.sample.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View

import com.appsamurai.ads.common.AdListener
import com.appsamurai.ads.common.AdRequest
import com.appsamurai.ads.common.MobileAds
import com.appsamurai.ads.interstitial.InterstitialAd

import java.util.logging.Level

class InterstitialAdActivity : AppCompatActivity() {

    // Add an InterstitialAd field
    private var mInterstitialAd: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interstitial_ad)

        // Define log level
        MobileAds.setLogLevel(Level.ALL)

        // Initialize the SDK with App Unit ID
        MobileAds.initialize(applicationContext, "gJIwJ-T0Kst86Mw3JIk-1A")

        // Create an InterstitialAd instance and set Ad Unit ID
        mInterstitialAd = InterstitialAd(this)
        val adUnitId = "nnrgOQ8JmbRCupYRQyNQwg"
        mInterstitialAd!!.adUnitId = adUnitId

        // Set an AdListener in order to track the state of the ad
        mInterstitialAd!!.adListener = object : AdListener() {

            // Code to be executed when an ad finishes loading.
            override fun onAdLoaded() {
                Log.d(Utils.LOGTAG, "Ad Loaded: $adUnitId")
            }

            // Code to be executed when an ad request fails.
            override fun onAdFailedToLoad(errorCode: Int) {
                Log.d(Utils.LOGTAG, "Ad Failed To Load: $adUnitId $errorCode")
            }

            // Code to be executed when an ad opens an overlay that covers the screen.
            override fun onAdOpened() {
                Log.d(Utils.LOGTAG, "Ad Opened: $adUnitId")
            }

            // Code to be executed when when the user is about to return to the app after tapping on an ad.
            override fun onAdClosed() {
                Log.d(Utils.LOGTAG, "Ad Closed: $adUnitId")
            }

            // Code to be executed when the user has left the app.
            override fun onAdLeftApplication() {
                Log.d(Utils.LOGTAG, "Ad Left Application: $adUnitId")
            }
        }
    }

    fun loadInterstitial(view: View) {
        // Create an AdRequest and load ad with this AdRequest
        val adRequest = AdRequest.Builder().build()
        mInterstitialAd!!.loadAd(adRequest)
    }

    fun showInterstitial(view: View) {
        // Before showing interstitial check if it is loaded
        if (mInterstitialAd!!.isLoaded) {
            mInterstitialAd!!.show()
        } else {
            Log.d(Utils.LOGTAG, "Interstitial was not ready to be shown.")
        }
    }
}
