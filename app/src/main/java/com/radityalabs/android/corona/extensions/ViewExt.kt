package com.radityalabs.android.corona.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.create(@LayoutRes resource: Int): View {
    return LayoutInflater.from(this.context).inflate(resource, this, false)
}
