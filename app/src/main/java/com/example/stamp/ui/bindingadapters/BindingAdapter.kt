package com.example.stamp.ui.bindingadapters

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("android:src")
fun setImageFromBitmap(view: ImageView, bitmap: Bitmap) {
    view.setImageBitmap(bitmap)
}