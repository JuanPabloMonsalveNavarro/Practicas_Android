package com.example.deudoresapp.ui.update

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.deudoresapp.DeudoresApp
import com.example.deudoresapp.R
import com.example.deudoresapp.data.dao.DebtorDao
import com.example.deudoresapp.data.entities.Debtor
import com.example.deudoresapp.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {

    companion object {
        fun newInstance() = UpdateFragment()
    }

    private lateinit var viewModel: UpdateViewModel
    private var _binding: FragmentUpdateBinding? = null

    private val binding get() = _binding!!
    private var isSearching = true


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =  FragmentUpdateBinding.inflate(inflater, container, false)
        val root: View = binding.root


        var idDebtor = 0
        binding.updateButton.setOnClickListener {   //buscando
            val debtorDao: DebtorDao = DeudoresApp.database.DebtorDao()
            val name = binding.nameEditText.text.toString()


            if(isSearching){    //Buscando
                var debtor: Debtor = debtorDao.readDebtor(name)
                if(debtor!=null){
                    idDebtor=debtor.id
                    binding.nameAmountEditText.setText(debtor.amount.toString())
                    binding.namePhoneEditText.setText(debtor.phone)
                    binding.updateButton.text = getString(R.string.title_update)
                    isSearching = false
                }else{
                    Toast.makeText(requireContext(), "No existe", Toast.LENGTH_SHORT).show()
                    cleanWitgets()
                }
            }else{  //Actualizando

                val debtor = Debtor(
                    id = idDebtor,
                    name = binding.nameEditText.text.toString(),
                    amount = binding.nameAmountEditText.text.toString().toLong(),
                    phone = binding.namePhoneEditText.text.toString()
                )
                debtorDao.updateDebtor(debtor)
                binding.updateButton.text = getString(R.string.title_update)
                isSearching=true
                cleanWitgets()
                Toast.makeText(requireContext(), "Deudor actualizado", Toast.LENGTH_SHORT).show()

            }
        }



        return root

    }

    private fun cleanWitgets() {
        with(binding){
            nameEditText.setText("")
            namePhoneEditText.setText("")
            nameAmountEditText.setText("")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UpdateViewModel::class.java)
        // TODO: Use the ViewModel
    }

}