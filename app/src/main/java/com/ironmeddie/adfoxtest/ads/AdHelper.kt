package com.ironmeddie.adfoxtest.ads

import android.app.Activity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ironmeddie.adfoxtest.R
import com.yandex.mobile.ads.banner.BannerAdSize
import com.yandex.mobile.ads.banner.BannerAdView
import com.yandex.mobile.ads.common.AdRequest
import kotlin.math.roundToInt

class AdHelper(private val activity: Activity) {

    private val adSize: BannerAdSize
        get() {
            val resources = activity.resources
            val screenHeight = resources.displayMetrics.run { heightPixels / density }.roundToInt()
            // Calculate the width of the ad, taking into account the padding in the ad container.
            val layout: ConstraintLayout = activity.findViewById(R.id.main)
            var adWidthPixels = layout.width
            if (adWidthPixels == 0) {
                // If the ad hasn't been laid out, default to the full screen width
                adWidthPixels = resources.displayMetrics.widthPixels
            }
            val adWidth = (adWidthPixels.div(resources.displayMetrics.density)).roundToInt()
            val maxAdHeight = screenHeight / 2

            return BannerAdSize.inlineSize(activity, adWidth, maxAdHeight)
        }

    fun loadBannerAd(): BannerAdView {
        val size = adSize
        val adView: BannerAdView = activity.findViewById(R.id.adContainerView)
        return adView.apply {
            setAdSize(size)
            setAdUnitId(context.getString(R.string.adUid))
            val onDestroy = {
                adView.destroy()
            }
            setBannerAdEventListener(
                MyBannerAdEventListener(
                    activity,
                    onDestroy
                )
            )
            loadAd(
                AdRequest.Builder()
                    // Methods in the AdRequest.Builder class can be used here to specify individual options settings.
                    .build()
            )
        }
    }

}