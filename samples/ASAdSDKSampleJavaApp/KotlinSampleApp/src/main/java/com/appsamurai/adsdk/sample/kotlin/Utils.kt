package com.appsamurai.adsdk.sample.kotlin

import android.content.res.Resources

/**
 * Created by olcay on 27.09.2018.
 */

object Utils {
    val LOGTAG = "AppSamuraiSampleApp"

    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

}
