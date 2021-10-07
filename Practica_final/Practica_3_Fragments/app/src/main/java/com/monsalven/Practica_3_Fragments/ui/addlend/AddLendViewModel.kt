package com.monsalven.Practica_3_Fragments.ui.addlend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class AddLendViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is add cosa"
    }
    val text: LiveData<String> = _text
}