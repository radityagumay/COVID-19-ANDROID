package com.radityalabs.android.corona

import android.app.Application
import com.radityalabs.android.corona.di.Injector
import com.radityalabs.android.corona.network.NetworkModule

class CovidApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initGraph()
    }

    private fun initGraph() {
        NetworkModule.Factory().create().let(Injector::add)
    }
}