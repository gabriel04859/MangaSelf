package com.gabssa.mangaself.ui.base

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.gabssa.mangaself.MainActivity
import com.gabssa.mangaself.R
import com.gabssa.mangaself.extensions.isVisible
import com.gabssa.mangaself.utils.PermissionUtil.Companion.MY_CAMERA_REQUEST_CODE
import com.google.android.material.bottomnavigation.BottomNavigationView

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private  val TAG = "BaseFragment"

    private var _binding: VB? = null
    protected val binding get() = _binding

    abstract fun inflateFragment(inflater: LayoutInflater, container: ViewGroup?): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = inflateFragment(inflater, container)

        return _binding?.root
    }

    protected fun bottomNavigatorVisibility(isVisibility: Boolean) {
        val bottomNavigator =
            (activity as MainActivity).findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigator.isVisible(isVisibility)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == MY_CAMERA_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "onRequestPermissionsResult: PERMISSION_GRANTED")
            } else {
                Log.d(TAG, "onRequestPermissionsResult: PERMISSION_DENNIED")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}