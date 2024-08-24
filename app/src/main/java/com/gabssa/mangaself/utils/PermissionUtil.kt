package com.gabssa.mangaself.utils

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionUtil(private var context: Activity) {

    fun checkIfPermissionIsGranted(permission: String) {

        if (ContextCompat.checkSelfPermission(context, permission)
            != PackageManager.PERMISSION_GRANTED) {
            requestPermission(permission)
        } else {

        }
    }

    private fun requestPermission(permission: String) {
        ActivityCompat.requestPermissions(context,
            arrayOf(permission),
            MY_CAMERA_REQUEST_CODE)
    }

    companion object {
        const val MY_CAMERA_REQUEST_CODE = 100
    }
}