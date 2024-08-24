package com.gabssa.mangaself.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheet<VB: ViewBinding>: BottomSheetDialogFragment() {

    private var _binding: VB? = null

    protected val binding: VB? get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = inflateView(layoutInflater, container)

        return binding?.root
    }

    protected abstract fun inflateView(layoutInflater: LayoutInflater, viewGroup: ViewGroup?): VB


}