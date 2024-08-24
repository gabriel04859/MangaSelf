package com.gabssa.mangaself.ui.add

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.gabssa.mangaself.databinding.BottomsheetPickimageBinding
import com.gabssa.mangaself.ui.base.BaseBottomSheet
import java.security.Permission

class PickImageBottomSheet : BaseBottomSheet<BottomsheetPickimageBinding>() {

    override fun inflateView(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?
    ): BottomsheetPickimageBinding {
        return BottomsheetPickimageBinding.inflate(layoutInflater, viewGroup, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.buttonClose?.setOnClickListener {
            dismiss()
        }

        binding?.openCamera?.setOnClickListener {
            openCamera()
        }

        binding?.openFile?.setOnClickListener {
            openFilePicker()
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (permissions.all { it.value }) {
                // All permissions are granted, proceed with the action
            } else {
                // Some permissions are denied, show a message to the user
            }
        }

    private fun checkAndRequestPermissions(permissions: List<String>) {
        val permissionsToRequest = mutableListOf<String>()

        permissions.forEach { permission ->
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissionsToRequest.add(permission)
            }
        }

        if (permissionsToRequest.isNotEmpty()) {
            requestPermissionLauncher.launch(permissionsToRequest.toTypedArray())
        }
    }

    private val cameraLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val photo = result.data?.extras?.get("data") as Bitmap

            }
        }

    private val filePickerLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val uri = result.data?.data

            }
        }


    private fun openCamera() {
        checkAndRequestPermissions(listOf(Manifest.permission.CAMERA))
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraLauncher.launch(cameraIntent)
    }

    private fun openFilePicker() {
        checkAndRequestPermissions(listOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE))
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        filePickerLauncher.launch(intent)
    }

}
