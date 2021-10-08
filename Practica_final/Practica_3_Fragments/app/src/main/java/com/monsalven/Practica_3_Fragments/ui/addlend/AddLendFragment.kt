package com.monsalven.Practica_3_Fragments.ui.addlend

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
import com.monsalven.Practica_3_Fragments.AdminActivity
import com.monsalven.Practica_3_Fragments.MainActivity
import com.monsalven.Practica_3_Fragments.R
import com.monsalven.Practica_3_Fragments.databinding.AddLendFragmentBinding
import com.monsalven.Practica_3_Fragments.databinding.AddObjectFragmentBinding
import com.monsalven.Practica_3_Fragments.model.Lend
import com.monsalven.Practica_3_Fragments.model.Object
import com.monsalven.Practica_3_Fragments.texto
import com.monsalven.Practica_3_Fragments.ui.addobject.AddObjectViewModel
import java.io.ByteArrayOutputStream
import java.sql.Types
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*


class AddLendFragment : Fragment() {

    private lateinit var createViewModel: AddLendViewModel
    private var _binding: AddLendFragmentBinding? = null
    private var backPressedTime = 0L

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var urlImage: String? = null
    private var REQUEST_IMAGE_CAPTURE = 1000
    val db = Firebase.firestore
    val obj = Object();


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        createViewModel =
            ViewModelProvider(this).get(AddLendViewModel::class.java)

        _binding = AddLendFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //      val textView: TextView = binding.textDashboard
        createViewModel.text.observe(viewLifecycleOwner, Observer {
            //          textView.text = it
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
                lendButton.isEnabled = true
                obj.id = texto
                /*Aquí se carga la info del objeto  que está en texto*/
                db.collection("Objects")
                    .whereEqualTo("id", obj.id)
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            Log.d("objetos", "${document.id} => ${document.data.getValue("name")}")
                            obj.name = document.data.getValue("name").toString()
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

            lendButton.setOnClickListener {
                lendButton.isEnabled = false
                if(texto != ""){saveLend(obj)}
            }



        }
        return root
    }

    private fun saveLend(obj: Object) {
        val date = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = date.format(Date())
        val documento = db.collection("Lends").document()
        val id = documento.id
        val lend = Lend(id = id, idObjeto = obj.id, name = obj.name, status = "Prestado", ced = "oritavemos_que_pedo", start_time = currentDate, finish_time = "En uso", urlPicture=obj.urlPicture)
        db.collection("Lends").document(id).set(lend)
        Toast.makeText(activity, getString(R.string.SuccesLend), Toast.LENGTH_SHORT).show()
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