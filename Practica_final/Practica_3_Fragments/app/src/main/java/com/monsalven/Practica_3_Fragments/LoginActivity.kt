package com.monsalven.Practica_3_Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.zxing.integration.android.IntentIntegrator
import com.monsalven.Practica_3_Fragments.databinding.ActivityLoginBinding
import com.monsalven.Practica_3_Fragments.model.User

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


        /*Acciones a realizar cuando se presione el boton de registro*/
        loginBinding.bttnRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }



        /*Acciones a realizar cuando se presione el boton de Entrar*/
        loginBinding.bttnLogin.setOnClickListener {


            val email = loginBinding.userNameEditText.text.toString()
            val password = loginBinding.passwordEditText.text.toString()


            /*Se comparan el nuevo_usuario y el usuario_registrado*/

            if (!email.isEmpty() || !password.isEmpty()) {
                /*Login con el Firebase*/
                signIn(email, password)

            } else {
                Toast.makeText(this, getString(R.string.empty_field), Toast.LENGTH_SHORT).show()
            }

        }

    }
    private fun signIn(email : String,password :  String) {
        val db = Firebase.firestore
        var admin_p: Boolean = false;
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Login", "signInWithEmail:success")
                    val user_id = auth.currentUser?.uid
                    db.collection("users")
                        .whereEqualTo("id", user_id)
                        .get()
                        .addOnSuccessListener { result ->
                            for (document in result) {
                                Log.d("poderes", "${document.id} => ${document.data.getValue("adminpower")}")
                                //println(document.data.getValue("adminpower").toString())
                                admin_p = document.data.getValue("adminpower").toString().toBoolean()
                            }
                            print("admin_p -> ")
                            println(admin_p)
                            if(admin_p){
                                startActivity(Intent(this@LoginActivity, AdminActivity::class.java))
                                println("Me fui por la ruta admin")
                            }else{
                                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                                println("Me fui por la ruta normal user")
                            }

                        }
                        .addOnFailureListener { exception ->
                            Log.w("nombre", "Error getting documents.", exception)
                        }

                } else {
                    var msg = ""
                    if(task.exception?.localizedMessage  == "The email address is badly formatted."){
                        msg = "Digite un correo válido" }
                    else if(task.exception?.localizedMessage  == "There is no user record corresponding to this identifier. The user may have been deleted."){
                    msg = "No existe una cuenta con este correo"}
                    else if(task.exception?.localizedMessage  == "The password is invalid or the user does not have a password."){
                        msg = "Contraseña y/o correo incorrecto"}
                    Log.w("Login", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, msg,
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }

    }
}
