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
import com.monsalven.practica_2_login.databinding.ActivityRegisterBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        loginBinding.bttnRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        val ttb = AnimationUtils.loadAnimation(this, R.anim.ttb);
        val stb = AnimationUtils.loadAnimation(this, R.anim.stb);
        val btt = AnimationUtils.loadAnimation(this, R.anim.btt);
        val btt2 = AnimationUtils.loadAnimation(this, R.anim.btt2);
        val btt3 = AnimationUtils.loadAnimation(this, R.anim.btt3);

        val main_tittle_login = findViewById(R.id.main_tittle_login) as TextView
        main_tittle_login.startAnimation(ttb)

        val logo_imageView = findViewById(R.id.logo_imageView) as ImageView
        logo_imageView.startAnimation(stb)

        val login_cardView = findViewById(R.id.login_cardView) as CardView
        login_cardView.startAnimation(btt)

        val register_linearLayout = findViewById(R.id.register_linearLayout) as LinearLayout
        register_linearLayout.startAnimation(btt2)
    }
}