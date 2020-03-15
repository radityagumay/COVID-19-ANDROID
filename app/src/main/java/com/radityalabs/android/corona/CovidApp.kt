package com.radityalabs.android.corona

import android.app.Application
import com.mapbox.mapboxsdk.Mapbox
import com.radityalabs.android.corona.di.Injector
import com.radityalabs.android.corona.network.NetworkModule

class CovidApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initGraph()
        initMapBox()
    }

    private fun initGraph() {
        Injector.provide<NetworkModule.Factory>()
    }

    private fun initMapBox() {
        Mapbox.getInstance(
            this,
            "pk.eyJ1IjoicmFkaXR5YWd1bWF5IiwiYSI6ImNrN3RhbWtxaDB5Z2IzZWx1czRzbml1cHcifQ.D2fLnq3cb7xY2NkC-ORbPQ"
        )
    }
}
