package com.radityalabs.android.corona.features.main.presentation.main

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.distinctUntilChanged
import com.radityalabs.android.corona.R
import com.radityalabs.android.corona.features.widget.SystemUiManager
import com.radityalabs.android.corona.features.widget.getThemeColor
import dev.chrisbanes.insetter.doOnApplyWindowInsets
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.LazyThreadSafetyMode.NONE

class MainActivity : AppCompatActivity() {
    private val statusBarColors by lazy(NONE) { SystemUiManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setupStatusBarColors()
        setupContentContainer()
        setupNavView()
        setupToolbar()
        setupDrawerLayout()
        setupFragment(savedInstanceState)
    }

    private fun setupNavView() {
        navView.doOnApplyWindowInsets { view, insets, initialState ->
            view.apply {
                val leftSpace = insets.systemWindowInsetLeft + initialState.paddings.left
                updatePadding(left = leftSpace)
                updateLayoutParams {
                    if (getWidth() > 0) {
                        width = measuredWidth + leftSpace
                    }
                }
            }
        }
    }

    private fun setupContentContainer() {
        contentContainer.doOnApplyWindowInsets { view, insets, initialState ->
            view.updatePadding(
                left = insets.systemWindowInsetLeft + initialState.paddings.left,
                right = insets.systemWindowInsetRight + initialState.paddings.right
            )
        }
    }

    private fun setupStatusBarColors() {
        statusBarColors.initial = true
        statusBarColors.isIndigoBackground = true
        statusBarColors.systemUiVisibility.distinctUntilChanged()
            .observe(this, Observer { visibility ->
                window.decorView.systemUiVisibility = visibility
            })
        statusBarColors.statusBarColor.distinctUntilChanged().observe(this, Observer { color ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.statusBarColor = color
            }
        })
        statusBarColors.navigationBarColor.distinctUntilChanged().observe(this, Observer { color ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.navigationBarColor = color
            }
        })
    }

    private fun setupToolbar() {
        val iconTint = getThemeColor(R.attr.colorOnPrimary)
        toolbar.navigationIcon = AppCompatResources.getDrawable(this, R.drawable.ic_menu_black_24dp)
            .apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    this?.setTint(iconTint)
                }
            }
        toolbar.setBackgroundColor(getThemeColor(R.attr.colorPrimarySurface))
        toolbar.setTitleTextColor(getThemeColor(R.attr.colorOnPrimarySurface))
        toolbar.doOnApplyWindowInsets { view, insets, initialState ->
            view.updateLayoutParams<ConstraintLayout.LayoutParams> {
                topMargin = insets.systemWindowInsetTop + initialState.margins.top
            }
        }
    }

    private fun setupDrawerLayout() {
        drawerLayout.doOnApplyWindowInsets { _, insets, _ ->
            drawerLayout.setChildInsetsWorkAround(insets)
        }
        drawerLayout.addDrawerListener(object : DrawerLayout.SimpleDrawerListener() {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                statusBarColors.drawerSlideOffset = slideOffset
            }
        })
        drawerLayout.setBackgroundColor(getThemeColor(R.attr.colorPrimary))
        drawerLayout.setStatusBarBackgroundColor(getThemeColor(R.attr.colorPrimarySurface))
    }

    @SuppressLint("RestrictedApi")
    private fun DrawerLayout.setChildInsetsWorkAround(insets: WindowInsetsCompat) {
        setChildInsets(
            insets.toWindowInsets(), insets.systemWindowInsetTop > 0
        )
    }

    private fun setupFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}
