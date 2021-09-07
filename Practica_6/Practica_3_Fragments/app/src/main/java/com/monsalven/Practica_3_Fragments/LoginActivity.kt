package com.monsalven.Practica_3_Fragments

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.monsalven.Practica_3_Fragments.databinding.ActivityLoginBinding
import com.monsalven.Practica_3_Fragments.extension.compare_users

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding: ActivityLoginBinding

    private lateinit var auth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        /*Cosas del Firebase*/
        auth = Firebase.auth

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

        val objectIntent: Intent = intent
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
            val email = loginBinding.userNameEditText.text.toString()
            val password = loginBinding.passwordEditText.text.toString()
            nuevo_usuario.email = email
            nuevo_usuario.password = password

            /*Se comparan el nuevo_usuario y el usuario_registrado*/

            if (!email.isEmpty() || !password.isEmpty()) {
                /* Login viejo
                if (compare_users(nuevo_usuario, usuario_registrado)) {
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                } else {
                    //Se realizan las dos verificaciones correspondientes
                    Toast.makeText(this, getString(R.string.wrong_password), Toast.LENGTH_SHORT).show()
                }*/

                /*Login con el Firebase*/


            } else {
                Toast.makeText(this, getString(R.string.empty_field), Toast.LENGTH_SHORT).show()
            }

        }

    }
}
