package com.ironmeddie.adfoxtest

import android.app.Application
import com.ironmeddie.adfoxtest.secret.Const
import io.appmetrica.analytics.AppMetrica
import io.appmetrica.analytics.AppMetricaConfig

class AdsApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initAppMetrica()
    }

    private fun initAppMetrica() {
        val config = AppMetricaConfig.newConfigBuilder(Const.API_KEY).build()
        AppMetrica.activate(this, config)
    }
}