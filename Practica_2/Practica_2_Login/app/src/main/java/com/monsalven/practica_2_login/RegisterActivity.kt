package com.monsalven.practica_2_login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContentProviderCompat.requireContext
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.monsalven.practica_2_login.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        val ttb = AnimationUtils.loadAnimation(this, R.anim.ttb);
        val btt = AnimationUtils.loadAnimation(this, R.anim.btt);

        val main_register_login = findViewById(R.id.main_register_login) as TextView
        main_register_login.startAnimation(ttb)

        val login_cardView = findViewById(R.id.login_cardView) as CardView
        login_cardView.startAnimation(btt)
    }

}