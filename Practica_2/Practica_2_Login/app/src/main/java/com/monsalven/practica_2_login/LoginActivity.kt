package com.monsalven.practica_2_login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.monsalven.practica_2_login.databinding.ActivityLoginBinding
import com.monsalven.practica_2_login.extension.compare_users
import com.monsalven.practica_2_login.usuario_registrado

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        /*Declaración y Activasión de animaciones*/
        val ttb = AnimationUtils.loadAnimation(this, R.anim.ttb)
        val stb = AnimationUtils.loadAnimation(this, R.anim.stb)
        val btt = AnimationUtils.loadAnimation(this, R.anim.btt)
        val btt2 = AnimationUtils.loadAnimation(this, R.anim.btt2)

        val mainTittleLogin = findViewById<TextView>(R.id.main_tittle_login)
        mainTittleLogin.startAnimation(ttb)
        val logoImageView = findViewById<ImageView>(R.id.logo_imageView)
        logoImageView.startAnimation(stb)
        val loginCardView = findViewById<CardView>(R.id.login_cardView)
        loginCardView.startAnimation(btt)
        val registerLinearLayout = findViewById<LinearLayout>(R.id.register_linearLayout)
        registerLinearLayout.startAnimation(btt2)

        val objectIntent : Intent = intent
        var email = objectIntent.getStringExtra("email")
        var password = objectIntent.getStringExtra("password")

        loginBinding.userNameEditText.setText(usuario_registrado.email)
        loginBinding.passwordEditText.setText(usuario_registrado.password)

        /*Acciones a realizar cuando se presione el boton de registro*/
        loginBinding.bttnRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        /*Acciones a realizar cuando se presione el boton de Entrar*/
        loginBinding.bttnLogin.setOnClickListener {
            /*Almacenamiento dle nuevo que se intenta loggear*/
            var nuevo_usuario = User() //usuario
            val email= loginBinding.userNameEditText.text.toString()
            val password = loginBinding.passwordEditText.text.toString()
            nuevo_usuario.email = email
            nuevo_usuario.password = password

            /*Se comparan el nuevo_usuario y el usuario_registrado*/

            if(!email.isEmpty() || !password.isEmpty()) {
                if (compare_users(nuevo_usuario, usuario_registrado)) {
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                } else {
                    //Se realizan las dos verificaciones correspondientes
                    Toast.makeText(this, getString(R.string.wrong_password), Toast.LENGTH_SHORT).show()
                }
            } else{
                Toast.makeText(this, getString(R.string.empty_field), Toast.LENGTH_SHORT).show()
            }

        }

    }
}
