package com.appsamurai.adsdk.sample;

import android.content.res.Resources;

/**
 * Created by olcay on 27.09.2018.
 */

public class Utils {
    public final static String LOGTAG = "AppSamuraiSampleApp";

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

}
