package com.gabssa.mangaself.ui.add

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.gabssa.mangaself.R
import com.gabssa.mangaself.databinding.FragmentAddBinding
import com.gabssa.mangaself.ui.base.BaseFragment
import com.gabssa.mangaself.utils.TextValidator

class AddFragment : BaseFragment<FragmentAddBinding>() {

    override fun inflateFragment(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAddBinding {
        return FragmentAddBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.also {
            val fields = listOf(
                it.editName,
                it.editAuthor,
                it.editBrazilianPublisher,
                it.editTotalEditions,
                it.editCurrentEditions
            )
            TextValidator(fields).fieldsObservables { isVisible ->
                binding?.buttonRegister?.isEnabled = isVisible
            }

            it.customToolbar.rightIconClickListener {
                findNavController().popBackStack()
            }
        }
    }
}