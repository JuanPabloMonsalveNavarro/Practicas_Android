package com.monsalven.Practica_3_Fragments.ui.myprofile

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.monsalven.Practica_3_Fragments.MainActivity
import com.monsalven.Practica_3_Fragments.R
import com.monsalven.Practica_3_Fragments.databinding.AddObjectFragmentBinding
import com.monsalven.Practica_3_Fragments.databinding.MyProfileFragmentBinding
import com.monsalven.Practica_3_Fragments.model.User
import com.monsalven.Practica_3_Fragments.ui.addobject.AddObjectViewModel
import com.squareup.picasso.Picasso
import java.io.ByteArrayOutputStream

class MyProfileFragment : Fragment() {

    private lateinit var createViewModel: MyProfileViewModel
    private var _binding: MyProfileFragmentBinding? = null
    private var backPressedTime = 0L


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val db = Firebase.firestore
    private var auth: FirebaseAuth = Firebase.auth
    var id_current_user: String? = auth.currentUser?.uid

    var resultLaucher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        if(result.resultCode== Activity.RESULT_OK){
            val data: Intent? = result.data
            val imageBitmap = data?.extras?.get("data") as Bitmap
            binding.userImageView.setImageBitmap(imageBitmap)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        db.collection("users")
            .whereEqualTo("id", id_current_user)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("nombre", "${document.id} => ${document.data.getValue("name")}")
                    var name = document.data.getValue("name").toString()
                    var email = document.data.getValue("email").toString()
                    var phone = document.data.getValue("phone").toString()
                    var ced = document.data.getValue("ced").toString()
                    var url = document.data.getValue("urlPicture").toString()
                    var adminpower = document.data.getValue("adminpower").toString()
                    var vinculation = document.data.getValue("vinculation").toString()
                    var hint_spinner: Int = 0

                    when(document.data.getValue("vinculation").toString()){
                        "Estudiante" -> {hint_spinner = 0}
                        "Egresado" -> {hint_spinner = 1}
                        "Profesor" -> {hint_spinner = 2}
                    }

                    with(binding){
                        if(url!=null) {
                            Picasso.get().load(url).into(userImageView);
                            println(url)
                        }
                        nameEditText.hint = name
                        emailEditText.hint = email
                        phoneEditText.hint = phone
                        cedEditText.hint = ced
                        vinculationSpinner.setSelection(hint_spinner)

                        updateButton.setOnClickListener {

                            /*Nueva imagen*/


                            val storage = FirebaseStorage.getInstance()
                            val pictureRef = storage.reference.child(id_current_user.toString())

                            binding.userImageView.isDrawingCacheEnabled = true
                            binding.userImageView.buildDrawingCache()
                            val bitmp = (binding.userImageView.drawable as BitmapDrawable).bitmap
                            val baos = ByteArrayOutputStream()
                            bitmp.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                            val data = baos.toByteArray()

                            val uploadTask = pictureRef.putBytes(data)
                            val urlTask = uploadTask.continueWithTask { task ->
                                if (!task.isSuccessful) {
                                    task.exception?.let {
                                        throw it
                                    }
                                }
                                pictureRef.downloadUrl
                            }.addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    val downloadUri = task.result


                                    if(nameEditText.text.toString()!=""){name=nameEditText.text.toString()}
                                    if(emailEditText.text.toString()!=""){email=emailEditText.text.toString()}
                                    if(phoneEditText.text.toString()!=""){phone=phoneEditText.text.toString()}
                                    if(cedEditText.text.toString()!=""){ced=cedEditText.text.toString()}
                                    vinculation = vinculationSpinner.selectedItem.toString()



                                    var user = User(id = id_current_user, email = email, name = name, phone = phone, vinculation = vinculation,ced = ced, adminpower = false, urlPicture =downloadUri.toString())
                                    id_current_user?.let { it1 ->
                                        db.collection("users").document(it1)
                                            .set(user)
                                            .addOnSuccessListener { documentReference ->
                                                Log.d("createInDB", "DocumentSnapshot added with ID: ${id_current_user}")
                                            }
                                            .addOnFailureListener { e ->
                                                Log.w("createInDB", "Error adding document", e)
                                            }
                                    }



                                } else {
                                    // Handle failures
                                    // ...
                                }
                            }


                            /*Nueva imagen*/


                        }

                        userImageView.setOnClickListener {
                            dispatchTakePictureIntent()
                        }
                    }

                }

            }
            .addOnFailureListener { exception ->
                Log.w("nombre", "Error getting documents.", exception)
            }

        createViewModel =
            ViewModelProvider(this).get(MyProfileViewModel::class.java)

        _binding = MyProfileFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //      val textView: TextView = binding.textDashboard
        createViewModel.text.observe(viewLifecycleOwner, Observer {
            //          textView.text = it
        })
        return root
    }

    private fun dispatchTakePictureIntent() {

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        resultLaucher.launch(intent)
    }



}