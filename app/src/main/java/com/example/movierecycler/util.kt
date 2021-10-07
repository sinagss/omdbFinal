package com.example.movierecycler

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

fun ImageView.loadUrl(url: String) {
    Glide.with(this).load(url).into(this)
}

fun Any.showLongToast(context: Context?, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun Any.showShortToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun View.hideKeyboard() {
    val iM =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    iM.hideSoftInputFromWindow(windowToken, 0)
}