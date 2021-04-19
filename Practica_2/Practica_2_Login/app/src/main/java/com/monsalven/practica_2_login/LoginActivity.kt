package com.monsalven.practica_2_login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.monsalven.practica_2_login.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        loginBinding.bttnRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

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
    }
}