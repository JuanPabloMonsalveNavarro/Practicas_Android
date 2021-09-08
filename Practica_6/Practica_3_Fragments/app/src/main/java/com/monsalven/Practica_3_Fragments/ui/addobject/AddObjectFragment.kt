package com.monsalven.Practica_3_Fragments.ui.addobject

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.lifecycle.Observer
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.monsalven.Practica_3_Fragments.MainActivity
import com.monsalven.Practica_3_Fragments.R
import com.monsalven.Practica_3_Fragments.databinding.AddObjectFragmentBinding
import com.monsalven.Practica_3_Fragments.model.Object
import java.sql.Types

class AddObjectFragment : Fragment() {

    private lateinit var createViewModel: AddObjectViewModel
    private var _binding: AddObjectFragmentBinding? = null
    private var backPressedTime = 0L

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

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
            addButton.setOnClickListener{
                val name = nameEditText.text.toString()
                val phone = stateEditText.text.toString()


                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)

                createObject(name, phone)
            }

            cancelButton.setOnClickListener{
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
            }

        }
        return root
    }

    private fun createObject(name: String, state: String) {
        //val new_object : Object(name = name, )
        if (name.isEmpty() || state.isEmpty()) {
            //Toast.makeText(this, getString(R.string.empty_field), Toast.LENGTH_SHORT).show()
        }
        else{
            val db = Firebase.firestore
            val document= db.collection("Objects").document()
            val id = document.id
            val Objeto = Object(name = name, state = state, status = "Disponible")
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