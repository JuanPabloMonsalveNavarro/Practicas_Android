package com.monsalven.Practica_3_Fragments.ui.historylends


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.monsalven.Practica_3_Fragments.R
import com.monsalven.Practica_3_Fragments.databinding.FragmentHistoryDetailBinding
import com.monsalven.Practica_3_Fragments.model.Lend
import com.squareup.picasso.Picasso


class HistoryDetailFragment : Fragment() {


    private var _binding : FragmentHistoryDetailBinding? = null
    private val args : HistoryDetailFragmentArgs by navArgs()
    val db = Firebase.firestore

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentHistoryDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        with(binding){
            val lend = args.lend

            db.collection("users")
                .whereEqualTo("id", lend.id_lender)
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        Log.d("nombre", "${document.id} => ${document.data.getValue("name")}")
                        nameTextStringView.text = document.data.getValue("name").toString()
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w("nombre", "Error getting documents.", exception)
                }

            titleTextView.text = lend.name
            descriptionTextView.text = lend.status
            StartTimeTextView.text = lend.start_time
            FinishTimeTextView.text = lend.finish_time
            if(lend.urlPicture != null){
                Picasso.get().load(lend.urlPicture).into(imageView)
            }

        }

        return root
    }
}
