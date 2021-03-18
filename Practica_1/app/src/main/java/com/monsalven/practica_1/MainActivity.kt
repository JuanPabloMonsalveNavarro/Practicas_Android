package com.monsalven.practica_1

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.autofill.AutofillValue
import android.widget.Toast
import com.monsalven.practica_1.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

//Variable para definir elementos vacios y espacios
private const val EMPTY = ""
private const val SPACE = " "

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    //Lista de usuarios que se van creando
    private var users: MutableList<User> = mutableListOf()

    //Variables para el calendario
    private var birthDay: String = ""
    private var cal = Calendar.getInstance()
    private var year = cal.get(Calendar.YEAR)
    private var month = cal.get(Calendar.MONTH)
    private var day = cal.get(Calendar.DAY_OF_MONTH)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        val dateSetListener =
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    cal.set(Calendar.YEAR, year)
                    cal.set(Calendar.MONTH, month)
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    val format = "MM/dd/yyyy"
                    val sdf = SimpleDateFormat(format, Locale.US)
                    birthDay = sdf.format(cal.time).toString()
                    mainBinding.dateButton.setText(birthDay)
                }

        mainBinding.dateButton.setOnClickListener { DatePickerDialog(this, dateSetListener, year, month, day).show() }

        //Acciones a realizar cuando se de click en el botón Guardar
        mainBinding.saveButton.setOnClickListener {
            val name        = mainBinding.nameEditText.text.toString()
            val email       = mainBinding.emailEditText.text.toString()
            val password    = mainBinding.passwordEditText.text.toString()
            val repPassword = mainBinding.repPasswordEditText.text.toString()
            val genre       = if (mainBinding.femaleRadioButton.isChecked) getString(R.string.female)
                                else (if (mainBinding.maleRadioButton.isChecked) getString(R.string.male)
                                        else getString(R.string.other))

            var hobbies = if (mainBinding.sportCheckBox.isChecked) getString(R.string.sport) + SPACE else EMPTY
                hobbies = if (mainBinding.readCheckBox.isChecked) hobbies + getString(R.string.read) + SPACE else hobbies + EMPTY
                hobbies = if (mainBinding.eatCheckBox.isChecked) hobbies + getString(R.string.eat) + SPACE else hobbies + EMPTY
                hobbies = if (mainBinding.danceCheckBox.isChecked) hobbies + getString(R.string.dance) else hobbies + EMPTY

            val city = mainBinding.birthSpinner.selectedItem.toString()

            //Verificación de que ningún campo esté vacío y de que las contraseñas coincidan entre sí
            if(name.isEmpty()) {
                mainBinding.nameTextInputLayout.error = getString(R.string.name_error)
            } else { if(email.isEmpty()){
                    mainBinding.emailTextInputLayout.error = getString(R.string.email_error)
                } else { if(password.isEmpty()){
                        mainBinding.passwordTextInputLayout.error = getString(R.string.password_error_null)
                    } else { if(repPassword.isEmpty()){
                            mainBinding.repPasswordInputLayout.error = getString(R.string.repPassword_error_null)
                        } else { if(!mainBinding.sportCheckBox.isChecked and
                                    !mainBinding.readCheckBox.isChecked and
                                    !mainBinding.eatCheckBox.isChecked and
                                    !mainBinding.danceCheckBox.isChecked){
                                Toast.makeText(this, getString(R.string.hobbies_error), Toast.LENGTH_LONG).show()
                            } else { if(password == repPassword){
                                    saveUser(name, email, password, genre, hobbies, birthDay, city)
                                    cleanViews()
                                    mainBinding.nameTextInputLayout.error     = null
                                    mainBinding.emailTextInputLayout.error    = null
                                    mainBinding.passwordTextInputLayout.error = null
                                    mainBinding.repPasswordInputLayout.error  = null
                                } else{
                                    mainBinding.repPasswordInputLayout.error = getString(R.string.password_error)
                                }
                            }
                        }
                    }
                }
            }
        }
    }//hola

    //Función que guarda y crea un nuvevo usuario con sus atributos
    private fun saveUser(name: String, email: String, password: String, genre: String, hobbies: String, birthday: String, city: String) {
        var newUser = User(name, email, password, genre, hobbies, birthday, city)
        users.add(newUser)
        printUserData()
    }

    //Función que permite crear el string que se mostrará en pantalla con los atributos de un usuario
    private fun printUserData() {
        var info = ""
        for(user in users) {
            info  = info + "Nombre completo: " + user.name + "\nCorreo electrónico: " + user.email + "\nGénero: " + user.genre + "\nPasatiempos: " + user.hobbies + "\nFecha nacimiento: " + user.birthday + "\nCiudad nacimiento:" + user.city + "\n\n"
        }
        mainBinding.printTextView.text = info
    }

    //Función que permite limpiar los campos después de haber creado un usuario
    private fun cleanViews() {
        with(mainBinding){
            nameEditText.setText(EMPTY)
            emailEditText.setText(EMPTY)
            passwordEditText.setText(EMPTY)
            repPasswordEditText.setText(EMPTY)
            femaleRadioButton.isChecked = true
            sportCheckBox.isChecked = false
            readCheckBox.isChecked = false
            eatCheckBox.isChecked = false
            danceCheckBox.isChecked = false
        }
    }
}