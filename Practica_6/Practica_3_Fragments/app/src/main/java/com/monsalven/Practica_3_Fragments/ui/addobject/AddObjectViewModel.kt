package com.monsalven.Practica_3_Fragments.ui.addobject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddObjectViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is add cosa"
    }
    val text: LiveData<String> = _text
}