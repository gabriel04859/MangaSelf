package com.gabssa.mangaself.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class TextValidator(private val textFields: List<EditText>) {

    fun fieldsObservables(allFull: (Boolean) -> Unit) {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validateFields {
                    allFull.invoke(it)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        setTextWatcher(textWatcher)
    }

    private fun validateFields(allFull: (Boolean) -> Unit) {
        val allFieldsFilled = textFields.all { it.text.trim().isNotEmpty() }
        allFull.invoke(allFieldsFilled)
    }

    private fun setTextWatcher(textWatcher: TextWatcher) {
        textFields.forEach {
            it.addTextChangedListener(textWatcher)
        }
    }
}