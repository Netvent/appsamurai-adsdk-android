package com.appsamurai.adsdk.sample.kotlin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun gotoProgrammaticBannerAd(view: View) {
        gotoActivity(ProgrammaticBannerAdActivity::class.java)
    }

    fun gotoLayoutBannerAd(view: View) {
        gotoActivity(LayoutBannerAdActivity::class.java)
    }

    fun gotoInterstitialAd(view: View) {
        gotoActivity(InterstitialAdActivity::class.java)
    }

    fun gotoVideoAd(view: View) {
        gotoActivity(VideoAdActivity::class.java)
    }

    private fun gotoActivity(cls: Class<*>) {
        val intent = Intent(this, cls)
        startActivity(intent)
    }
}
