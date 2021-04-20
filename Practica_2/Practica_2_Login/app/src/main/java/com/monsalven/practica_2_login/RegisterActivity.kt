package com.monsalven.practica_2_login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.monsalven.practica_2_login.databinding.ActivityRegisterBinding
import com.monsalven.practica_2_login.extension.isValidEmail
import com.monsalven.practica_2_login.extension.isValidPhone
import com.monsalven.practica_2_login.extension.validate
import com.monsalven.practica_2_login.User
var usuario_registrado = User(); //usuario global

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        /*Verificaciones en loscampos del formulario*/
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

        /*Acciones a realizar cuando se presione el boton de registro*/
        registerBinding.bttnRegister.setOnClickListener {
            val name        = registerBinding.userNameEditText.text.toString()
            val email       = registerBinding.emailEditText.text.toString()
            val phone       = registerBinding.phoneEditText.text.toString()
            val password    = registerBinding.passwordEditText.text.toString()
            val repPassword = registerBinding.repPasswordEditText.text.toString()
            val vinculation = registerBinding.vinculationSpinner.selectedItem.toString()

            /*Verificación de la coincidencia entre contraseñas y campos vacíos*/
            if(name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || repPassword.isEmpty()) {
                Toast.makeText(this, getString(R.string.empty_field), Toast.LENGTH_SHORT).show()
            }
            else{
                if(password != repPassword){
                    registerBinding.repPassword.error = getString(R.string.password_error)
                }
                else{
                    registerBinding.repPassword.error  = null
                    /*Capturar usuario y guardarlo en el objeto usuario*/
                    //startActivity(Intent(this@RegisterActivity, LoginActivity::class.java).putExtra("email", email).putExtra("password", password))
                    save_user(name, email, phone, password, vinculation);
                    startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))

                }
            }
        }
    }

    /*Definición del textWatcher para verificar que la contraseña tenga el número necesario de carácteres*/
    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) {
            if(s.toString().length < 6) {
                registerBinding.password.error = getString(R.string.password_required)
            }
            else{
                registerBinding.password.error = null
            }
        }
    }
}

private fun save_user(name: String, email: String, phone: String, password: String,  vinculation: String){
    usuario_registrado .name=name;
    usuario_registrado .email=email;
    usuario_registrado.phone = phone;
    usuario_registrado .password = password;
    usuario_registrado .vinculation = vinculation;
}
