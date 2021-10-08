package com.monsalven.Practica_3_Fragments.ui.returnobject

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.zxing.integration.android.IntentIntegrator
import com.monsalven.Practica_3_Fragments.R
import com.monsalven.Practica_3_Fragments.databinding.AddLendFragmentBinding
import com.monsalven.Practica_3_Fragments.databinding.ReturnObjectFragmentBinding
import com.monsalven.Practica_3_Fragments.model.Lend
import com.monsalven.Practica_3_Fragments.model.Object
import com.monsalven.Practica_3_Fragments.texto
import com.monsalven.Practica_3_Fragments.ui.addlend.AddLendViewModel
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Picture
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.preference.PreferenceManager
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.lifecycle.Observer
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.monsalven.Practica_3_Fragments.*
import com.monsalven.Practica_3_Fragments.databinding.AddObjectFragmentBinding
import com.monsalven.Practica_3_Fragments.ui.addobject.AddObjectViewModel
import java.io.ByteArrayOutputStream
import java.sql.Types
import java.util.*



class ReturnObjectFragment : Fragment() {

    private lateinit var createViewModel: ReturnObjectViewModel
    private var _binding: ReturnObjectFragmentBinding? = null
    private var backPressedTime = 0L

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    val db = Firebase.firestore
    private var auth: FirebaseAuth = Firebase.auth
    var id_lender: String? = auth.currentUser?.uid
    val obj = Object();


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        createViewModel =
            ViewModelProvider(this).get(ReturnObjectViewModel::class.java)

        _binding = ReturnObjectFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        createViewModel.text.observe(viewLifecycleOwner, Observer {
        })

        with(binding){

            /*
            takePictureObjectImageView.setOnClickListener{
                dispatchTakePictureIntent()
            }*/

            qrToLendImageView.setOnClickListener {
                initScanner();
                LoadButton.isEnabled = true
            }

            LoadButton.setOnClickListener {
                LoadButton.isEnabled = false
                returnButton.isEnabled = true

                obj.id = texto



                /*Aquí se carga la info del objeto  que está en texto*/
                db.collection("Objects")
                    .whereEqualTo("id", obj.id)
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            Log.d("objetos", "${document.id} => ${document.data.getValue("name")}")
                            obj.name = document.data.getValue("name").toString()
                            obj.status = document.data.getValue("status").toString()
                            obj.state = document.data.getValue("state").toString()
                            obj.urlPicture = document.data.getValue("urlPicture").toString()

                            objectNameTextView.text = obj.name
                            objectStateTextView.text = obj.state


                            if(obj.urlPicture!=null){
                                Picasso.get().load(obj.urlPicture).into(objectToLendImageView)
                            }
                        }

                    }
            }

            returnButton.setOnClickListener {
                returnButton.isEnabled = false

                if(texto != ""){
                    ReturnObj(obj)
                }

            }



        }
        return root
    }

    private fun ReturnObj(obj: Object) {
        val date = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = date.format(Date())
        var lend =  Lend();



        if(obj.status == "Prestado"){
            db.collection("Lends")
                .whereEqualTo("idObjeto", obj.id)
                .whereEqualTo("status", "Prestado")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        Log.d("prestamos", "${document.id} => ${document.data.getValue("name")}")
                        lend.id = document.data.getValue("id").toString()
                        lend.idObjeto = document.data.getValue("idObjeto").toString()
                        lend.name = document.data.getValue("name").toString()
                        lend.status = "Finalizado"
                        lend.id_lender = document.data.getValue("id_lender").toString()
                        lend.start_time = document.data.getValue("start_time").toString()
                        lend.finish_time = currentDate
                        lend.urlPicture = document.data.getValue("urlPicture").toString()


                        println("arroz")
                        println(lend)

                        db.collection("Lends").document(lend.id.toString()).set(lend)
                    }
                }


            db.collection("Objects")
                .whereEqualTo("id", obj.id)
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        Log.d("objetos", "${document.id} => ${document.data.getValue("name")}")

                        obj.name = document.data.getValue("name").toString()
                        obj.state = document.data.getValue("state").toString()
                        obj.status = "Disponible"
                        obj.urlPicture = document.data.getValue("urlPicture").toString()
                        db.collection("Objects").document(obj.id.toString()).set(obj)
                    }

                }


            Toast.makeText(activity, getString(R.string.SuccesReturn), Toast.LENGTH_SHORT).show()
            texto = ""

        }else{
            Toast.makeText(activity, "Objeto no No prestado", Toast.LENGTH_SHORT).show()
        }

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


}