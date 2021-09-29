package com.example.practica_8.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practica_8.R
import com.example.practica_8.databinding.AnimeListItemBinding
import com.example.practica_8.model.Document
import com.squareup.picasso.Picasso

class AnimeAdapter(
    private val onItemClicked: (Document) -> Unit,
) : RecyclerView.Adapter<AnimeAdapter.ViewHolder>(){

    private var listAnimes: MutableList<Document> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.anime_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimeAdapter.ViewHolder, position: Int) {
        holder.bind(listAnimes[position])
        holder.itemView.setOnClickListener{ onItemClicked(listAnimes[position])}
    }

    override fun getItemCount(): Int = listAnimes.size

    fun appendItems(newItems: MutableList<Document>){
        listAnimes.clear()
        listAnimes.addAll(newItems)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val binding = AnimeListItemBinding.bind(view)
        private val context: Context = binding.root.context
        fun bind(document: Document){
            with(binding){
                titleTextView.text = document.titles?.en
                dateTextView.text = context.getString(R.string.realese_info, document.startDate)
                episodesTextView.text = context.getString(R.string.episodes_info, document.episodesCount.toString())
                if(document.coverImage != null){
                    Picasso.get().load(document.coverImage).into(pictureImageView)
                }
            }
        }

    }
}