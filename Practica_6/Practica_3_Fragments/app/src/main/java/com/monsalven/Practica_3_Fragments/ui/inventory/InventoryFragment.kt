package com.monsalven.Practica_3_Fragments.ui.inventory

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.monsalven.Practica_3_Fragments.databinding.InventoryFragmentBinding
import com.monsalven.Practica_3_Fragments.R
import com.monsalven.Practica_3_Fragments.model.Object

class InventoryFragment : Fragment() {

    private lateinit var inventoryViewModel: InventoryViewModel
    private var _binding: InventoryFragmentBinding? = null
    private lateinit var objectsAdapter: ObjectAdabter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inventoryViewModel =
            ViewModelProvider(this).get(InventoryViewModel::class.java)

        _binding = InventoryFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root


        //  val textView: TextView = binding.textHome
        inventoryViewModel.text.observe(viewLifecycleOwner, Observer {
            //textView.text = it
        })

        objectsAdapter= ObjectAdabter()
        binding.debtorReclyclerView.apply {
            layoutManager = LinearLayoutManager(this@InventoryFragment.context)
            adapter = objectsAdapter
            setHasFixedSize(false)
        }
        /*
        val debtorDAO: DebtorDao = DeudoresApp.database.DebtorDao()
        val listDebtors: MutableList<Debtor> = debtorDAO.getDebtors()
        debtorsAdapter.appendItems(listDebtors)
        */

        loadFromServer()

        return root
    }

    private fun loadFromServer() {
        val db = Firebase.firestore
        db.collection("Objects").get().addOnSuccessListener { result ->

            var listObjetos : MutableList<Object> = arrayListOf()
            for (document in result){
                Log.d("objeto", document.data.toString())
                listObjetos.add(document.toObject<Object>())
            }
            objectsAdapter.appendItems(listObjetos)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}