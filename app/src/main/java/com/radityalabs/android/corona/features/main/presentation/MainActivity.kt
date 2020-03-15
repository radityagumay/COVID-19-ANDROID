package com.radityalabs.android.corona.features.main.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.distinctUntilChanged
import com.radityalabs.android.corona.R
import com.radityalabs.android.corona.features.widget.SystemUiManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val statusBarColors: SystemUiManager by lazy {
        SystemUiManager(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupStatusBarColors()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    private fun setupStatusBarColors() {
        statusBarColors.initial = true
        statusBarColors.isIndigoBackground = true

        drawerLayout.addDrawerListener(object : DrawerLayout.SimpleDrawerListener() {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                statusBarColors.drawerSlideOffset = slideOffset
            }
        })

        statusBarColors.systemUiVisibility.distinctUntilChanged()
            .observe(this, Observer { visibility ->
                window.decorView.systemUiVisibility = visibility
            })
        statusBarColors.statusBarColor.distinctUntilChanged().observe(this, Observer { color ->
            window.statusBarColor = color
        })
        statusBarColors.navigationBarColor.distinctUntilChanged().observe(this, Observer { color ->
            window.navigationBarColor = color
        })
    }
}
