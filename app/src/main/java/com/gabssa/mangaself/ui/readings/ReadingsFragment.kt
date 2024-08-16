package com.gabssa.mangaself.ui.readings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gabssa.mangaself.R
import com.gabssa.mangaself.databinding.FragmentReadingsBinding
import com.gabssa.mangaself.ui.base.BaseFragment

class ReadingsFragment : BaseFragment<FragmentReadingsBinding>() {

    override fun inflateFragment(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentReadingsBinding {
        return FragmentReadingsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}