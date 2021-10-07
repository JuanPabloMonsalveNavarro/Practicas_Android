package com.monsalven.Practica_3_Fragments.ui.addobject

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Picture
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.preference.PreferenceManager
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.lifecycle.Observer
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.google.zxing.integration.android.IntentIntegrator
import com.monsalven.Practica_3_Fragments.MainActivity
import com.monsalven.Practica_3_Fragments.R
import com.monsalven.Practica_3_Fragments.databinding.AddObjectFragmentBinding
import com.monsalven.Practica_3_Fragments.model.Object
import java.io.ByteArrayOutputStream
import java.sql.Types


class AddObjectFragment : Fragment() {

    private lateinit var createViewModel: AddObjectViewModel
    private var _binding: AddObjectFragmentBinding? = null
    private var backPressedTime = 0L

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var urlImage: String? = null
    private var REQUEST_IMAGE_CAPTURE = 1000

    var resultLaucher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        if(result.resultCode== RESULT_OK){
            val data: Intent? = result.data
            val imageBitmap = data?.extras?.get("data") as Bitmap
            binding.takePictureObjectImageView.setImageBitmap(imageBitmap)
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        createViewModel =
            ViewModelProvider(this).get(AddObjectViewModel::class.java)

        _binding = AddObjectFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //      val textView: TextView = binding.textDashboard
        createViewModel.text.observe(viewLifecycleOwner, Observer {
            //          textView.text = it
        })

        with(binding){

            takePictureObjectImageView.setOnClickListener{
                dispatchTakePictureIntent()
            }

            captureQrImageView.setOnClickListener {

                initScanner();
            }


            addButton.setOnClickListener{
                savePicture()

                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
            }

            cancelButton.setOnClickListener{
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
            }

        }
        return root
    }

    private fun initScanner() {

        val integrator = IntentIntegrator(activity)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("Please focus the camera on the QR Code")
        integrator.setCameraId(0)
        integrator.setBeepEnabled(false)
        integrator.setBarcodeImageEnabled(false)
        integrator.initiateScan();
    }





    private fun savePicture() {
        val storage = FirebaseStorage.getInstance()
        val pictureRef = storage.reference.child("obajetos")

        binding.takePictureObjectImageView.isDrawingCacheEnabled = true
        binding.takePictureObjectImageView.buildDrawingCache()
        val bitmp = (binding.takePictureObjectImageView.drawable as BitmapDrawable).bitmap
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
                saveObject(downloadUri.toString())
            } else {
                // Handle failures
                // ...
            }
        }

    }

    private fun saveObject(urlPicture: String) {
        with(binding){
            val name = nameEditText.text.toString()
            val state = stateEditText.text.toString()
            createObject(name, state, urlPicture)
        }
    }
    private fun dispatchTakePictureIntent() {

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        resultLaucher.launch(intent)
    }

    private fun createObject(name: String, state: String, urlPicture: String) {
        //val new_object : Object(name = name, )
        if (name.isEmpty() || state.isEmpty()) {
            //Toast.makeText(this, getString(R.string.empty_field), Toast.LENGTH_SHORT).show()
        }
        else{
            val db = Firebase.firestore
            val document= db.collection("Objects").document()
            val id = document.id
            val Objeto = Object(id = id, name = name, state = state, status = "Disponible", urlPicture = urlPicture)
            db.collection("Objects").document(id).set(Objeto)
            cleanView()
        }


    }


    private fun cleanView() {
        with(binding){
            nameEditText.setText("")
            stateEditText.setText("")
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}