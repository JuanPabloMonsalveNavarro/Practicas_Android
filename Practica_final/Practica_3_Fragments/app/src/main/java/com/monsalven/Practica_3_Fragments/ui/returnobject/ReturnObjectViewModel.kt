package com.monsalven.Practica_3_Fragments.ui.returnobject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ReturnObjectViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is add cosa"
    }
    val text: LiveData<String> = _text
}