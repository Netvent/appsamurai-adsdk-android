
# App Samurai Ad SDK
## Download
The App Samurai AD SDK is available via:

**JCenter AAR**
    [ ![Download](https://api.bintray.com/packages/appsamurai/maven/core/images/download.svg) ](https://bintray.com/appsamurai/maven/core/_latestVersion)  
    The App Samurai AD SDK is available as an AAR via JCenter; to use it, add the following to your `build.gradle`.
    
   ``` java
   repositories {
       jcenter() // includes the App Samurai AD SDK
   }

   dependencies {
       implementation 'com.appsamurai.adsdk:core:0.2.7'
   }
   ```
    
## Requirements

- Android 4.0.4 (API Version 15) and up
- appcompat-v7, r28 
- retrofit v2.4.0 ( available on JCenter )
- converter-gson v2.4.0 ( available on JCenter )
- **Recommended** Google Play Services Ads 15.0.1


### Add Play Service Ads for Personalized Ads
Add play-services lib to app level build.config file ( choose a compatible version with your project )

``` java
dependencies {
    implementation 'com.google.android.gms:play-services-ads:15.0.1'
}
```

## Initialization
### SDK Initialization
```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

	// Initializing SDK
    MobileAds.initialize(getApplicationContext(), "<app-id>");
}
```
### SDK Logging
Use setLogLevel in order to set log level just before MobileAds.initialize()
Default log level is Level.ALL

Sample usage
``` java
// show all log messages
MobileAds.setLogLevel(Level.ALL)
// disable all log messages
MobileAds.setLogLevel(Level.OFF)
// show only warning and error log messages
MobileAds.setLogLevel(Level.WARNING)
```

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

// disable logs
MobileAds.setLogLevel(Level.OFF)
    MobileAds.initialize(getApplicationContext(), "<app-id>");
}
```

## Banner Integration
You have two options to create AdView:
### Step 1 : You have two options to create AdView
#### Option 1: Creating AdView from Layout and reaching this view from code
Create AdView from Layout and reach this view from code
``` xml
<com.appsamurai.ads.banner.AdView
	android:id="@+id/adView"
	android:layout_width="320dp"
	android:layout_height="50dp"
	android:layout_alignParentBottom="true"
	android:layout_centerHorizontal="true"
	ads:adUnitId="<ad-unit-id>">
```
``` java
AdView mAdView = findViewById(R.id.adView);
```
#### Option 2: Creating AdView programmatically and adding this view to a container view
``` java
mAdView = new AdView(this);
mAdView.setAdUnitId("<ad-unit-id>");
RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(Utils.dpToPx(320), Utils.dpToPx(50));
params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
adContainer.addView(mAdView, params);
```

### Step 2 : Create and load request
``` java
AdRequest adRequest = new AdRequest.Builder().build();
mAdView.loadAd(adRequest);
```
### Step 3 ( Optional ) : Set an Ad Listener if you want to listen the callback methods
``` java
mAdView.setAdListener(new AdListener() {
    @Override
    public void onAdLoaded() {
    }

    @Override
    public void onAdFailedToLoad(int errorCode) {
    }

    @Override
    public void onAdOpened() {
    }

    @Override
    public void onAdLeftApplication() {
    }

    @Override
    public void onAdClosed() {
    }
});
```

## Interstitial Integration
### Step 1: Create an InterstitialAd
``` java
private InterstitialAd mInterstitialAd;
```
``` java
mInterstitialAd = new InterstitialAd(context);
```

### Step 2: Set the ad unit id
``` java
mInterstitialAd.setAdUnitId("<ad-unit-id>");
```
### Step 3: Create and load request
``` java
AdRequest adRequest = new AdRequest.Builder().build();
mInterstitialAd.loadAd(adRequest);
```

### Step 4 ( Optional ) : Set an Ad Listener if you want to listen the callback methods
``` java
mInterstitialAd.setAdListener(new AdListener() {
    @Override
    public void onAdLoaded() {
    }

    @Override
    public void onAdFailedToLoad(int errorCode) {
    }

    @Override
    public void onAdOpened() {
    }

    @Override
    public void onAdLeftApplication() {
    }

    @Override
    public void onAdClosed() {
    }
});
```

### Step 5: Show the interstitial whenever you want if it is loaded
``` java
if (mInterstitialAd.isLoaded()) {
    mInterstitialAd.show();
}
```

## Rewarded Video Integration
### Step 1: Create an InterstitialAd
``` java
private RewardedVideoAd mRewardedVideoAd;
```
``` java
mRewardedVideoAd = new InterstitialAd(context);
```
### Step 2: Set the ad unit id
``` java
mRewardedVideoAd.setAdUnitId("<ad-unit-id>");
```
### Step 3: Create and load request
``` java
AdRequest adRequest = new AdRequest.Builder().build();
mRewardedVideoAd.loadAd(adRequest);
```
### Step 4 ( Optional ) : Set an Ad Listener if you want to listen the callback methods
``` java
mRewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
    @Override
    public void onRewardedVideoAdLoaded() {
    }

    @Override
    public void onRewardedVideoAdOpened() {
    }

    @Override
    public void onRewardedVideoStarted() {
    }

    @Override
    public void onRewardedVideoAdClosed() {
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
    }

    @Override
    public void onRewardedVideoCompleted() {
    }
});
```
### Step 5: Show the interstitial whenever you want if it is loaded
``` java
if (mRewardedVideoAd.isLoaded()) {
    mRewardedVideoAd.show();
}
```

