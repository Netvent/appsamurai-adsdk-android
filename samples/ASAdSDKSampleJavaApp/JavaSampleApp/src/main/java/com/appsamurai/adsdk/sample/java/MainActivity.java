package com.appsamurai.adsdk.sample.java;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gotoProgrammaticBannerAd(View view) {
        gotoActivity(ProgrammaticBannerAdActivity.class);
    }

    public void gotoLayoutBannerAd(View view) {
        gotoActivity(LayoutBannerAdActivity.class);
    }

    public void gotoInterstitialAd(View view) {
        gotoActivity(InterstitialAdActivity.class);
    }

    public void gotoVideoAd(View view) {
        gotoActivity(VideoAdActivity.class);
    }

    private void gotoActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }
}
