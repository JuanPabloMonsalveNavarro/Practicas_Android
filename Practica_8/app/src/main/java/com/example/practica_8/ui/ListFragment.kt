package com.example.practica_8.ui

import android.app.ListFragment
import android.net.DnsResolver
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practica_8.R
import com.example.practica_8.databinding.FragmentListBinding
import com.example.practica_8.model.AnimesList
import com.example.practica_8.model.Document
import com.example.practica_8.server.ApiService
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var animesAdapter: AnimeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        animesAdapter = AnimeAdapter(onItemClicked = {onAnimeItemClicked(it)})

        binding.animeRecyclerView.apply {

            layoutManager = LinearLayoutManager(this@ListFragment.context)
            adapter = animesAdapter
            setHasFixedSize(false)
        }

        loadAnimes()
        return root
    }

    private fun loadAnimes() {
        ApiService.create()
            .getAnime()
            .enqueue(object : retrofit2.Callback<AnimesList>{
                override fun onFailure(call: Call<AnimesList>, t: Throwable){
                    Log.d("Error", t.message.toString())
                }

                override fun onResponse(call: Call<AnimesList>, response: Response<AnimesList>) {
                    if(response.isSuccessful){
                        var listAnimes : MutableList<Document> = response.body()?.data?.documents as MutableList<Document>
                        animesAdapter.appendItems(listAnimes)
                    }
                }
            })

    }

    private fun onAnimeItemClicked(anime: Document) {

        findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(anime))

    }


}