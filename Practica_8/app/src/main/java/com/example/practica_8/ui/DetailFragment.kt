package com.example.practica_8.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.practica_8.R
import com.example.practica_8.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso


class DetailFragment : Fragment() {

    private var _binding : FragmentDetailBinding? = null
    private val args: DetailFragmentArgs by navArgs()

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        with(binding){
            val anime = args.document

            titleTextView.text = anime.titles?.en
            descriptionTextView.text = anime.descriptions?.en
            if(anime.bannerImage != null){
                Picasso.get().load(anime.bannerImage).into(imageView)
            }

        }

        return root
    }

}