package com.gabssa.mangaself.extensions

import android.view.View

fun View.isVisible(visibility: Boolean) {
    this.visibility = if (visibility) View.VISIBLE else View.GONE
}