package com.monsalven.practica_2_login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.monsalven.practica_2_login.databinding.ActivityRegisterBinding
import com.monsalven.practica_2_login.extension.isValidEmail
import com.monsalven.practica_2_login.extension.isValidPhone
import com.monsalven.practica_2_login.extension.validate


class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        /*Verificaciones*/
        registerBinding.userNameEditText.validate(getString(R.string.name_required)) { s -> s.length >= 6 }
        registerBinding.emailEditText.validate(getString(R.string.email_required)) {s -> s.isValidEmail()}
        registerBinding.phoneEditText.validate(getString(R.string.phone_required)) {s -> s.isValidPhone()}

        registerBinding.passwordEditText.addTextChangedListener(textWatcher)

        /*Declaración y Activasión de animaciones*/
        val ttb = AnimationUtils.loadAnimation(this, R.anim.ttb)
        val btt = AnimationUtils.loadAnimation(this, R.anim.btt)

        val mainRegisterLogin = findViewById<TextView>(R.id.main_register_login)
        mainRegisterLogin.startAnimation(ttb)
        val loginCardView = findViewById<CardView>(R.id.login_cardView)
        loginCardView.startAnimation(btt)

        registerBinding.bttnRegister.setOnClickListener {
            val name        = registerBinding.userNameEditText.text.toString()
            val email       = registerBinding.emailEditText.text.toString()
            val password    = registerBinding.passwordEditText.text.toString()
            val repPassword = registerBinding.repPasswordEditText.text.toString()
            val vinculation        = registerBinding.vinculationSpinner.selectedItem.toString()

            if(password != repPassword){
                registerBinding.repPassword.error = getString(R.string.password_error)
            }
            else{
                registerBinding.repPassword.error  = null
            }
        }
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) {
            if(s.toString().length <= 6) {
                registerBinding.password.error = getString(R.string.password_required)
            }
            else{
                registerBinding.password.error = null
            }
        }
    }
}