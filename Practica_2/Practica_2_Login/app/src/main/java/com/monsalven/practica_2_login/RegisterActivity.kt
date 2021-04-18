package com.monsalven.practica_2_login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.core.content.ContentProviderCompat.requireContext
import com.monsalven.practica_2_login.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        val genre = resources.getStringArray(R.array.genre_list)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, genre)
        registerBinding.genreEditText.setAdapter(arrayAdapter)

        val vinculation = resources.getStringArray(R.array.vinculation_list)
        val arrayAdapter2 = ArrayAdapter(this, R.layout.dropdown_item, vinculation)
        registerBinding.vinculoEditText.setAdapter(arrayAdapter)
    }

}