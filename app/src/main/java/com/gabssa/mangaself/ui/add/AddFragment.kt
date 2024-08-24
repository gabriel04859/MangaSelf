package com.gabssa.mangaself.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
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

        bottomNavigatorVisibility(false)

        setupEditTextListeners()

        binding?.customToolbar?.rightIconClickListener {
            findNavController().popBackStack()
        }

        binding?.buttonAddImagem?.setOnClickListener {
            Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT).show()
        }

        binding?.buttonAddImagem?.setOnClickListener {
            PickImageBottomSheet().also {
                it.show(childFragmentManager, it.tag)
            }
        }
    }

    private fun setupEditTextListeners() {
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
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bottomNavigatorVisibility(true)

    }
}