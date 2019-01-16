
# App Samurai Ad SDK

[![alt text](https://appsamurai.com/wp-content/uploads/2018/10/as_dark_logotype-8.png "AppSamurai")](https://www.appsamurai.com)

# Easy And Effective App Monetization

## Sample Projects
Check samples directory for sample **Java** and **Kotlin** projects. 

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
       // Be sure that you are using latest version
       implementation 'com.appsamurai.adsdk:core:1.0.0'
   }
   ```

## Supported SDK Versions
Minimum supported SDK version is 15. Be sure that minSdkVersion in your gradle file isn't lower than 15.

## SDK Version
You can get current version of the SDK with getSDKVersion method.
```java
String sdkVersion = MobileAds.getSDKVersion();
```
## Requirements

- Android 4.0.4 (API Version 15) and up
- appcompat-v7, r28 
- retrofit v2.4.0 ( available on JCenter )
- converter-gson v2.4.0 ( available on JCenter )
- **Recommended** Google Play Services Ads 15.0.1
- omsdk-android-<version>-Appsamurai ( Open Measurement SDK led by IAB )



### Add Play Service Ads for Personalized Ads
Add play-services lib to app level build.config file ( choose a compatible version with your project )

``` java
dependencies {
    implementation 'com.google.android.gms:play-services-ads:15.0.1'
}
```

## Initialization
### Required Permissions
As you can guest you need internet permission to get and show ads. Ad this line to AndroidManifest.xml if you have it alreeady.
``` xml
<uses-permission android:name="android.permission.INTERNET" />
```
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
You can user "AppSamurai" to filter AppSamurai Ad SDK logs. 

## Adding test devices
If you want to test your SDK integration without using live app id and ad unit ids, you can add your device as test device.

To see your device ID check the logcat output for a message that looks like this
```
D/AppSamurai: Use AdRequest.Builder.addTestDevice("YXBwc20tEzGiNDU5YzVlZWM3NzA4Zg==") to get test ads on this device.
```

Modify your code to call AdRequest.Builder.addTestDevice() with your test device ID. This method can be called multiple times for multiple devices.
```
AdRequest adRequest = new AdRequest.Builder()
.addTestDevice("YXBwc20tEzGiNDU5YzVlZWM3NzA4Zg==") // an example device ID
.build();
```

If you properly set your device as test device, at logcat you will seee a message that looks like this
```
D/AppSamurai: This request will be sent from a test device.
```

***Note*** : Android emulators are automatically configured as test devices.

## Possible Ad Request Error Cases
If you want to take action according to ad request error cases
```java
ERROR_CODE_INTERNAL_ERROR = 0;
ERROR_CODE_INVALID_REQUEST = 1;
ERROR_CODE_NETWORK_ERROR = 2;
ERROR_CODE_NO_FILL = 3;
```
## Banner Integration
You have two options to create AdView:
#### Step 1 : You have two options to create AdView
##### Option 1: Creating AdView from Layout and reaching this view from code
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
##### Option 2: Creating AdView programmatically and adding this view to a container view
``` java
// create an adview and set ad unit id
mAdView = new AdView(this);
mAdView.setAdUnitId("nnrgOQ4JmLRCuphTYTkRvg");

// create a layout params and add ad view to the container view with this layout params
RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(Utils.dpToPx(320), Utils.dpToPx(50));
params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
adContainer.addView(mAdView, params);
```

#### Step 2 : Create and load request
``` java
// create an ad request and load ad with this ad request
AdRequest adRequest = new AdRequest.Builder().build();
mAdView.loadAd(adRequest);
```
#### Step 3 ( Optional ) : Set an Ad Listener if you want to listen the callback methods
``` java
// set an AdListener in order to track the state of the ad
mAdView.setAdListener(new AdListener() {
    // Code to be executed when an ad finishes loading.
    @Override
    public void onAdLoaded() {
    }
    
    // Code to be executed when an ad request fails.
    @Override
    public void onAdFailedToLoad(int errorCode) {
    }
    
    // Code to be executed when an ad opens an overlay that covers the screen.
    @Override
    public void onAdOpened() {
    }
    
    // Code to be executed when the user has left the app.
    @Override
    public void onAdLeftApplication() {
    }
    
    // Code to be executed when when the user is about to return to the app after tapping on an ad.
    @Override
    public void onAdClosed() {
    }
});
```
#### Supported Banner Sizes
So far the only supported banner size is 320 x 50. 

### Interstitial Integration
#### Step 1: Create an InterstitialAd
``` java
// Add an InterstitialAd field
private InterstitialAd mInterstitialAd;
```
``` java
// Create an InterstitialAd instance
mInterstitialAd = new InterstitialAd(this);
```

#### Step 2: Set the ad unit id
``` java
// Set Ad Unit ID
mInterstitialAd.setAdUnitId("<ad-unit-id>");
```
#### Step 3: Create and load request
``` java
// Create an AdRequest and load ad with this AdRequest
AdRequest adRequest = new AdRequest.Builder().build();
mInterstitialAd.loadAd(adRequest);
```

#### Step 4 ( Optional ) : Set an Ad Listener if you want to listen the callback methods
``` java
// Set an AdListener in order to track the state of the ad
mInterstitialAd.setAdListener(new AdListener() {

    // Code to be executed when an ad finishes loading.
    @Override
    public void onAdLoaded() {
    }

    // Code to be executed when an ad request fails.
    @Override
    public void onAdFailedToLoad(int errorCode) {
    }

    // Code to be executed when an ad opens an overlay that covers the screen.
    @Override
    public void onAdOpened() {
    }

    // Code to be executed when when the user is about to return to the app after tapping on an ad.
    @Override
    public void onAdClosed() {
    }

    // Code to be executed when the user has left the app.
    @Override
    public void onAdLeftApplication() {
    }
});
```

#### Step 5: Show the interstitial whenever you want if it is loaded
``` java
// Before showing interstitial check if it is loaded
if (mInterstitialAd.isLoaded()) {
    mInterstitialAd.show();
}
```

#### Supported Media Formats
Interstial ad type supports both HTML and video ad formats. But AdListener methods are identical for both HTML and video.

### Rewarded Video Integration
#### Step 1: Create an InterstitialAd
``` java
// Add an RewardedVideoAd field
private RewardedVideoAd mRewardedVideoAd;
```
``` java
// Create an RewardedVideoAd instance
mRewardedVideoAd = new RewardedVideoAd(context);
```
#### Step 2: Set the ad unit id
``` java
// Set Ad Unit ID
mRewardedVideoAd.setAdUnitId("<ad-unit-id>");
```
#### Step 3: Create and load request
``` java
// Create an AdRequest and load ad with this AdRequest
AdRequest adRequest = new AdRequest.Builder().build();
mRewardedVideoAd.loadAd(adRequest);
```
#### Step 4 ( Optional ) : Set an Ad Listener if you want to listen the callback methods
``` java
// set an AdListener in order to track the state of the ad
mRewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {

    // Code to be executed when an ad finishes loading.
    @Override
    public void onRewardedVideoAdLoaded() {
    }

    // Code to be executed when an ad request fails.
    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
    }

    // Code to be executed when the ad is displayed.
    @Override
    public void onRewardedVideoAdOpened() {
    }

    // Code to be executed when the video is started.
    @Override
    public void onRewardedVideoStarted() {
    }

    // Code to be executed when the user has gained the reward.
    @Override
    public void onRewarded() {
    }

    // Code to be executed when the user has left the app.
    @Override
    public void onRewardedVideoAdLeftApplication() {
    }

    // Code to be executed when the video is completed.
    @Override
    public void onRewardedVideoCompleted() {
    }

    // Code to be executed when when the ad is closed.
    @Override
    public void onRewardedVideoAdClosed() {
    }

});
```
#### Step 5: Show the interstitial whenever you want if it is loaded
``` java
// Before showing interstitial check if it is loaded
if (mRewardedVideoAd.isLoaded()) {
    mRewardedVideoAd.show();
}
```

## Kotlin Developers
Check samples directory for sample Kotlin project.

[![alt text](https://appsamurai.com/wp-content/uploads/2014/12/web_home_cta_2.png "AppSamurai")](https://www.appsamurai.com)
