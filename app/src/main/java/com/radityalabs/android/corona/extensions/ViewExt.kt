package com.radityalabs.android.corona.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import com.squareup.picasso.Picasso

fun ViewGroup.create(@LayoutRes resource: Int): View {
    return LayoutInflater.from(this.context).inflate(resource, this, false)
}

fun ImageView.load(url: String, centerCrop: Boolean = true, fit: Boolean = true) {
    Picasso.get()
        .load(url)
        .also { if (centerCrop) it.centerCrop() }
        .also { if (fit) it.fit() }
        .into(this)
}
