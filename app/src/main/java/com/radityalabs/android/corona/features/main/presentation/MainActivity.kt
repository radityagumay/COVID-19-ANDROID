package com.radityalabs.android.corona.features.main.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.radityalabs.android.corona.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}
