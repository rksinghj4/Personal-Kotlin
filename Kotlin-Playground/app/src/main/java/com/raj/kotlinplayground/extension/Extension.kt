package com.raj.kotlinplayground.extension

import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * 1. loadImage() method is not directly add to the source code of ImageView
 * therefor we can access any private member of ImageView class inside loadImage() method
 *
 * 2. It is not a inheritance also. So we can't access private/protracted member of ImageView
 * here inside loadImage() method
 */
fun ImageView.loadImage(url: String) {
    Glide.with(this).load(url).into(this)
}

private fun main() {
    /**
     * val imageView = ImageView(this)
     * imageView.loadImage("xyz")
     */
}

