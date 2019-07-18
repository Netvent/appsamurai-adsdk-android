
# App Samurai Ad SDK

[![alt text](https://appsamurai.com/wp-content/uploads/2018/10/as_dark_logotype-8.png "AppSamurai")](https://www.appsamurai.com)

# Easy And Effective App Monetization

## Sample Projects
Check samples directory for sample **Java** and **Kotlin** projects. 

## Download
The App Samurai Ad SDK is available via:

**JCenter AAR**
    [ ![Download](https://api.bintray.com/packages/appsamurai/maven/core/images/download.svg) ](https://bintray.com/appsamurai/maven/core/_latestVersion)  
    The App Samurai Ad SDK is available as an AAR via JCenter; to use it, add the following to your `build.gradle`.
    
   ``` java
   repositories {
       jcenter() // includes the App Samurai Ad SDK
   }

   dependencies {
       // Be sure that you are using latest version
       implementation 'com.appsamurai.adsdk:core:1.2.1'
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
D/AppSamurai: Use AdRequest.Builder.addTestDevice("<Device ID>") to get test ads on this device.
```

Modify your code to call AdRequest.Builder.addTestDevice() with your test device ID. This method can be called multiple times for multiple devices.
```
AdRequest adRequest = new AdRequest.Builder()
.addTestDevice("<Device ID>") // an example device ID
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
mAdView.setAdUnitId("<ad-unit-id>");

// create a layout params and add ad view to the container view with this layout params
RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(Utils.dpToPx(320), Utils.dpToPx(50));
params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
adContainer.addView(mAdView, params);
```

Here is the helper method that you can use in order to convert dp to px
``` java
public static int dpToPx(int dp) {
    DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
    int px = (int) (dp * displayMetrics.density + .5f);
    return px;
}
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
Supported banner sizes are 320x50 ( BANNER ) and 300x250 ( MEDIUM_RECTANGLE ). 

#### Medium Rectangle Banner ( 300x250 )
For 300x250 banner size you must manually set ad sizes with two options. 

##### Option 1
Set width and height with layout_width and layout_height and than set ads:adSize to MEDIUM_RECTANGLE. Default value for ads:adSize is BANNER ( 320x50 )

``` xml
<com.appsamurai.ads.banner.AdView
    ............
    android:layout_width="300dp"
    android:layout_height="250dp"
    ads:adSize="MEDIUM_RECTANGLE"
    ............ >
```

##### Option 2
Second option is setting width and height of the container layout of the banner programmatically and than setting ad size with AdView.setAdSize(AdSize.MEDIUM_RECTANGLE) method.

``` java
// create an adview and set ad unit id
mAdView = new AdView(this);
mAdView.setAdUnitId("<ad-unit-id>");
mAdView.setAdSize(AdSize.MEDIUM_RECTANGLE);

// create a layout params and add ad view to the container view with this layout params
RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(Utils.dpToPx(300), Utils.dpToPx(250));
params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
adContainer.addView(mAdView, params);
```

#### Disabling Banner Auto Refresh
By default content of the banner is refreshed periodically. If you want to disable banner auto refresh you have two options

##### Option 1
From layout

``` xml
<com.appsamurai.ads.banner.AdView
    ............
    ads:autoLoadEnabled="false"
    ............ >
```

##### Option 2
Programmatically
``` java
    private AdView mAdView;

    ......
    mAdView = new AdView(this);
    mAdView.setAdUnitId("<ad-unit-id>");
    mAdView.setAutoLoadEnabled(false);
    ......
```


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

# Using Google AdMob with App Samurai Ads
Google AdMob is one of the most popular ad network that preferred by mobile application developers. If you want use App Samurai Ads but still needs a backup plan in order to maximize fill rate and eCPM, we have a ready to use solution for you AppSamurai Waterfall Ad SDK. By using App Samurai Waterfall Ad SDK we guarantee you to if App Samurai can't fill your ad request we will forward your request to Google AdMob.

Using App Samurai Waterfall SDK is very similar to App Samurai Ad SDK.

## Adding AppSamurai Waterfall Ad SDK to Your Project
**JCenter AAR**
    [ ![Download](https://api.bintray.com/packages/appsamurai/maven/waterfall/images/download.svg) ](https://bintray.com/appsamurai/maven/waterfall/_latestVersion)  
    The App Samurai Waterfall Ad SDK is available as an AAR via JCenter; to use it, add the following to your build.gradle.
``` java
repositories {
    jcenter() // includes the App Samurai Ad SDK
}

dependencies {
    // Be sure that you are using latest version
    implementation 'com.appsamurai.adsdk:waterfall:0.1.0'
}
```
## Google AdMob Integration
AppSamurai Waterfall Ad SDK helps you to use AppSamurai Ads and Google AdMob easily. But still you should import the AdMob Mobile Ads SDK and update your AndroidManifest.xml with YOUR_ADMOB_APP_ID.

**Update project level build.gradle**
``` java
allprojects {
    repositories {
        google()
        jcenter()
    }
}
```

**update app-level build.gradle**
``` java
dependencies {
    implementation 'com.google.android.gms:play-services-ads:17.2.1'
}
```

**Update your AndroidManifest.xml**
``` xml
<manifest>
    <application>
        <!-- Sample AdMob App ID: ca-app-pub-3940256099942544~3347511713 -->
        <meta-data
            android:name="com.google.android.gms.ads.APPLICATION_ID"
            android:value="YOUR_ADMOB_APP_ID"/>
    </application>
</manifest>
```

Check latest version of Google AdMob Integration from the AdMob documentations. https://developers.google.com/admob/android/quick-start




## SDK Initializing
update app-level build.gradle witk AppSamurai Core and Waterfall Ad SDKs
**Note: Be sure that you are using latest versions**
``` java
dependencies {
    implementation 'com.appsamurai.adsdk:core:1.4.1'
    implementation ('com.appsamurai.adsdk:waterfall:0.1.2') {
        exclude module: 'core'
    }
}
```

In order to initialize SDK you need to use MobileAds.initialize() method as early as possible. onCreate methods of the Application or MainActivity is the most proper place to initialize SDK.

```kotlin
// class imports
import com.appsamurai.waterfall.ad.MobileAds
import com.appsamurai.waterfall.ad.AdNetwork

// replace GOOGLE_ADS_APPLICATION_ID and APPSAMURAI_ADS_APPLICATION_ID 
// with your application's AppSamurai Ads and Google Ads ids
fun initializeSDK() {
    MobileAds.initialize(this, hashMapOf(
        AdNetwork.GOOGLE to "<GOOGLE_ADS_APPLICATION_ID>",
        AdNetwork.APPSAMURAI to "<APPSAMURAI_ADS_APPLICATION_ID">
    ))
}
```

## Banner Integration
### Step 1: Create Ad Container Layout
First of all you need an ad container layout. You can create this container layout programatically or from xml layout. Here is an example xml layout. adContainer linear layout will be the container for your banner.

```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <!--  put your content container here -->

    <!--  ad container  -->
    <LinearLayout
        android:id="@+id/adContainer"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerHorizontal="true"
        android:orientation="vertical"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent" />
</android.support.constraint.ConstraintLayout>
```

### Step 2: Defining a BannerAd
``` kotlin
// import list
import com.appsamurai.ads.common.AdListener
import com.appsamurai.ads.common.AdRequest
import com.appsamurai.ads.common.AdSize
import com.appsamurai.waterfall.ad.BannerAd
import com.appsamurai.waterfall.ad.MobileAds
import com.appsamurai.waterfall.ad.AdNetwork

// defining a banner ad
private var mBannerAd: BannerAd? = null
```

### Step 3: Creating the BannerAd
``` kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    ...
    
    // creating the banner ad
    // first parameter is the activity context and the second one is the ad container you defined in the layout
    mBannerAd = BannerAd("<activity context>", adContainer)
}
```

### Step 4: Setting ad unit ids
``` kotlin
mBannerAd?.adUnitIds?.put(AdNetwork.APPSAMURAI, "<APPSAMURAI_ADS_BANNER_ADUNIT_ID>")
mBannerAd?.adUnitIds?.put(AdNetwork.GOOGLE, "<GOOGLE_ADS_BANNER_ADUNIT_ID>")
```

### Step 5: Setting Banner Size
``` kotlin
mBannerAd?.adSize = AdSize.BANNER
```

### Step 6: Setting AdListener ( optional )
``` kotlin
mBannerAd?.setAdListener(object : AdListener(){
    // Code to be executed when an ad finishes loading.
    override fun onAdLoaded() {
    }

    // Code to be executed when an ad request fails.
    override fun onAdFailedToLoad(var1: Int) {
    }

    // Code to be executed when an ad opens an overlay that covers the screen.
    override fun onAdOpened() {
    }

    // Code to be executed when the user has left the app.
    override fun onAdLeftApplication() {
    }

    // Code to be executed when when the user is about to return to the app after tapping on an ad.
    override fun onAdClosed() {
    }
})
```

### Step 7: Loading ad request
``` kotlin
val adRequest = AdRequest.Builder().build()
mBannerAd?.loadAd(adRequest)
```
## Interstitial Integration
### Step 1: Defining an InterstitialAd
``` kotlin
import com.appsamurai.ads.common.AdListener
import com.appsamurai.ads.common.AdRequest
import com.appsamurai.waterfall.ad.InterstitialAd
import com.appsamurai.waterfall.ad.MobileAds
import com.appsamurai.waterfall.ad.AdNetwork

private var mInterstitialAd: InterstitialAd? = null
```

### Step 2: Creating the InterstitialAd
``` kotlin
mInterstitialAd = InterstitialAd(<activity context>)
```

### Step 3: Setting ad unit ids
``` kotlin
mInterstitialAd?.adUnitIds?.put(AdNetwork.APPSAMURAI, "<APPSAMURAI_ADS_INTERSTITIAL_ADUNIT_ID>")
mInterstitialAd?.adUnitIds?.put(AdNetwork.GOOGLE, "<GOOGLE_ADS_INTERSTITIAL_ADUNIT_ID>")
```

### Step 4: Setting AdListener
``` kotlin
mInterstitialAd?.setAdListener(object : AdListener(){
    // Code to be executed when an ad finishes loading.
    override fun onAdLoaded() {
    }

    // Code to be executed when an ad request fails.
    override fun onAdFailedToLoad(var1: Int) {
    }

    // Code to be executed when an ad opens an overlay that covers the screen.
    override fun onAdOpened() {
    }

    // Code to be executed when the user has left the app.
    override fun onAdLeftApplication() {
    }

    // Code to be executed when when the user is about to return to the app after tapping on an ad.
    override fun onAdClosed() {
    }
})
```
### Step 5: Loading ad request
``` kotlin
val adRequest = AdRequest.Builder().build()
mInterstitialAd?.loadAd(adRequest)
```

### Step 6: Showing ad
In order to be notified when the ad is ready to shown you should override the onAdLoaded() method of the AdListener that you set to the InterstitialAd.
```kotlin
if (mInterstitialAd?.isLoaded!!) {
    mInterstitialAd?.show()
}
```

## Rewarded Integration
Integration of the rewarded video ad is so similar to interstitial ad.

### Step 1: Defining an RewardedAd
``` kotlin
import com.appsamurai.ads.reward.RewardedVideoAdListener
import com.appsamurai.ads.common.AdRequest
import com.appsamurai.waterfall.ad.RewardedAd
import com.appsamurai.waterfall.ad.MobileAds
import com.appsamurai.waterfall.ad.AdNetwork

private var mRewardedAd: RewardedAd? = null
```

### Step 2: Creating the RewardedAd
``` kotlin
mRewardedAd = RewardedAd(<activity context>)
```

### Step 3: Setting ad unit ids
``` kotlin
mRewardedAd?.adUnitIds?.put(AdNetwork.APPSAMURAI, "<APPSAMURAI_ADS_REWARDED_ADUNIT_ID>")
mRewardedAd?.adUnitIds?.put(AdNetwork.GOOGLE, "<GOOGLE_ADS_REWARDED_ADUNIT_ID>")
```

### Step 4: Setting AdListener
``` kotlin
mRewardedAd?.setAdListener(object : RewardedVideoAdListener(){
    // Code to be executed when an ad finishes loading.
    override fun onRewardedVideoAdLoaded() {
    }

    // Code to be executed when an ad request fails.
    override fun onRewardedVideoAdFailedToLoad(errorCode: Int) {
    }

    // Code to be executed when the ad is displayed.
    override fun onRewardedVideoAdOpened() {
    }

    // Code to be executed when the video is started.
    override fun onRewardedVideoStarted() {
    }

    // Code to be executed when when the ad is closed.
    override fun onRewardedVideoAdClosed() {
    }

    // Code to be executed when the user has gained the reward.
    override fun onRewarded() {
    }

    // Code to be executed when the user has left the app.
    override fun onRewardedVideoAdLeftApplication() {
    }

    // Code to be executed when the video is completed.
    override fun onRewardedVideoCompleted() {
    }
})
```
### Step 5: Loading ad request
``` kotlin
val adRequest = AdRequest.Builder().build()
mRewardedAd?.loadAd(adRequest)
```

### Step 6: Showing ad
In order to be notified when the ad is ready to shown you should override the onRewardedVideoAdLoaded() method of the RewardedVideoAdListener that you set to the RewardedAd.
```kotlin
if (mRewardedAd?.isLoaded!!) {
    mRewardedAd?.show()
}
```

## Kotlin Developers
Check samples directory for sample Kotlin project.

[![alt text](https://appsamurai.com/wp-content/uploads/2014/12/web_home_cta_2.png "AppSamurai")](https://www.appsamurai.com)
