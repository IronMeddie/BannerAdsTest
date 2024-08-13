package com.ironmeddie.adfoxtest.ads

import android.app.Activity
import android.util.Log
import com.yandex.mobile.ads.banner.BannerAdEventListener
import com.yandex.mobile.ads.common.AdRequestError
import com.yandex.mobile.ads.common.ImpressionData

class MyBannerAdEventListener(
    private val activity: Activity,
    private val onDestroy: () -> Unit
) :
    BannerAdEventListener {

    private val TAG = "AdEventListenerTag"

    override fun onAdLoaded() {
        // If this callback occurs after the activity is destroyed, you
        // must call destroy and return or you may get a memory leak.
        // Note `isDestroyed` is a method on Activity.

        if (activity.isDestroyed) {
            onDestroy()
            return
        }
    }

    override fun onAdFailedToLoad(error: AdRequestError) {
        Log.w(
            TAG,
            "error while loading add with code: ${error.code} and description: ${error.description}"
        )
        // Ad failed to load with AdRequestError.
        // Attempting to load a new ad from the onAdFailedToLoad() method is strongly discouraged.
    }

    override fun onAdClicked() {
        // Called when a click is recorded for an ad.
    }

    override fun onLeftApplication() {
        // Called when user is about to leave application (e.g., to go to the browser), as a result of clicking on the ad.
    }

    override fun onReturnedToApplication() {
        // Called when user returned to application after click.
    }

    override fun onImpression(impressionData: ImpressionData?) {
        // Called when an impression is recorded for an ad.
    }
}