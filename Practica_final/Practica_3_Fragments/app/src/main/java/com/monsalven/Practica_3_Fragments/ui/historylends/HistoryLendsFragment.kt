package com.monsalven.Practica_3_Fragments.ui.historylends

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.monsalven.Practica_3_Fragments.databinding.HistoryLendsFragmentBinding
import com.monsalven.Practica_3_Fragments.databinding.MyPastLendsFragmentBinding

import com.monsalven.Practica_3_Fragments.model.Lend
import com.monsalven.Practica_3_Fragments.ui.mypastlends.LendsAdapter
import com.monsalven.Practica_3_Fragments.ui.mypastlends.MyPastLendsViewModel




class HistoryLendsFragment : Fragment() {

    private lateinit var historylendsViewModel: HistoryLendsViewModel
    private var _binding: HistoryLendsFragmentBinding? = null
    private lateinit var lendsAdapter: LendsAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    private var auth: FirebaseAuth = Firebase.auth
    var id_lender: String? = auth.currentUser?.uid

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        historylendsViewModel =
            ViewModelProvider(this).get(HistoryLendsViewModel::class.java)

        _binding = HistoryLendsFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root


        //  val textView: TextView = binding.textHome
        historylendsViewModel.text.observe(viewLifecycleOwner, Observer {
            //textView.text = it
        })

        lendsAdapter = LendsAdapter()
        binding.debtorReclyclerView.apply {
            layoutManager = LinearLayoutManager(this@HistoryLendsFragment.context)
            adapter = lendsAdapter
            setHasFixedSize(false)
        }
        loadFromServer()


        return root
    }

    private fun loadFromServer() {
        val db = Firebase.firestore
        db.collection("Lends")
            .get()
            .addOnSuccessListener { result ->
                var listLends : MutableList<Lend> = arrayListOf()
                for (document in result){
                    Log.d("lend", document.data.toString())
                    listLends.add(document.toObject<Lend>())
                }
                lendsAdapter.appendItems(listLends)
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}