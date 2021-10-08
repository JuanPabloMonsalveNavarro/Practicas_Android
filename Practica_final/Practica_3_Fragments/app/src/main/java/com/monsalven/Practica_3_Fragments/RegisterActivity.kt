package com.monsalven.Practica_3_Fragments

import android.provider.MediaStore
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.monsalven.Practica_3_Fragments.databinding.ActivityRegisterBinding
import com.monsalven.Practica_3_Fragments.extension.isValidEmail
import com.monsalven.Practica_3_Fragments.extension.isValidPhone
import com.monsalven.Practica_3_Fragments.extension.validate
import com.monsalven.Practica_3_Fragments.model.User
import java.io.ByteArrayOutputStream


class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var registerBinding: ActivityRegisterBinding

    lateinit var name : String
    lateinit var email  : String
    lateinit var phone  : String
    lateinit var password : String
    lateinit var repPassword : String
    lateinit var vinculation : String
    lateinit var ced : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        /*Firebase*/
        auth = Firebase.auth

        /*Verificaciones en loscampos del formulario*/
        registerBinding.userNameEditText.validate(getString(R.string.name_required)) { s -> s.length >= 6 }
        registerBinding.emailEditText.validate(getString(R.string.email_required)) { s -> s.isValidEmail() }
        registerBinding.phoneEditText.validate(getString(R.string.phone_required)) { s -> s.isValidPhone() }
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
            name = registerBinding.userNameEditText.text.toString()
            email = registerBinding.emailEditText.text.toString()
            phone = registerBinding.phoneEditText.text.toString()
            password = registerBinding.passwordEditText.text.toString()
            repPassword = registerBinding.repPasswordEditText.text.toString()
            vinculation = registerBinding.vinculationSpinner.selectedItem.toString()
            ced = registerBinding.cedInputText.text.toString()

            /*Verificación de la coincidencia entre contraseñas y campos vacíos*/
            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || repPassword.isEmpty()) {
                Toast.makeText(this, getString(R.string.empty_field), Toast.LENGTH_SHORT).show()
            } else {
                if (password != repPassword) {
                    registerBinding.repPassword.error = getString(R.string.password_error)
                } else {
                    registerBinding.repPassword.error = null
                    /*Capturar usuario y guardarlo en el objeto usuario*/
                    //save_user(name, email, phone, password, vinculation);
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener() { task ->
                            if (task.isSuccessful) {
                                Log.d("register", "createUserWithEmail:success")


                                createUser()
                                Toast.makeText(baseContext, "Registro exitoso",
                                    Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))



                            } else {
                                var msg = ""
                                if(task.exception?.localizedMessage  == "The email address is badly formatted."){
                                    msg = "Digite un correo válido" }
                                else if(task.exception?.localizedMessage  == "The given password is invalid. [ Password should be at least 6 characters ]"){
                                    msg = "La contraseña debe contener 6 caracteres" }
                                else if(task.exception?.localizedMessage  == "The email address is already in use by another account."){
                                    msg = "Este correo ya tiene una cuenta asociada" }
                                // If sign in fails, display a message to the user.
                                Log.w("registe", "createUserWithEmail:failure", task.exception)
                                Toast.makeText(baseContext, msg,
                                    Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            }

        }


    }

    private fun createUser() {
        var id = auth.currentUser?.uid
        id?.let{ id ->
            val user = User(id = id, email = email, name = name, phone = phone, vinculation = vinculation,ced = ced, adminpower = false)
            val db = Firebase.firestore
            db.collection("users").document(id)
                .set(user)
                .addOnSuccessListener { documentReference ->
                    Log.d("createInDB", "DocumentSnapshot added with ID: ${id}")
                }
                .addOnFailureListener { e ->
                    Log.w("createInDB", "Error adding document", e)
                }
        }

    }



    /*Definición del textWatcher para verificar que la contraseña tenga el número necesario de carácteres*/
    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) {
            if (s.toString().length < 6) {
                registerBinding.password.error = getString(R.string.password_required)
            } else {
                registerBinding.password.error = null
            }
        }
    }

}

/*
private fun save_user(name: String, email: String, phone: String, password: String, vinculation: String) {
    usuario_registrado.name = name;
    usuario_registrado.email = email;
    usuario_registrado.phone = phone;
    usuario_registrado.password = password;
    usuario_registrado.vinculation = vinculation;
}*/

