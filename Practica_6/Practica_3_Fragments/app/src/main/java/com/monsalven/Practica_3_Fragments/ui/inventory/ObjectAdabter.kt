package com.monsalven.Practica_3_Fragments.ui.inventory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.monsalven.Practica_3_Fragments.R
import com.monsalven.Practica_3_Fragments.databinding.CardViewObjectsItemBinding
import com.monsalven.Practica_3_Fragments.model.Object
import com.squareup.picasso.Picasso

class ObjectAdabter : RecyclerView.Adapter<ObjectAdabter.ViewHolder>(){

    private var listObject : MutableList<Object> = mutableListOf()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_objects_item, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listObject[position])
    }

    override fun getItemCount(): Int {
        return listObject.size
    }

    fun appendItems(newItems : MutableList<Object>){
        listObject.clear()
        listObject.addAll(newItems)
        notifyDataSetChanged()

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val binding = CardViewObjectsItemBinding.bind(view)
        fun bind(obje: Object){
            with(binding){
                nameTextView.text = obje.name
                statusTextView.text = obje.status
                if (obje.urlPicture != null){
                    Picasso.get().load(obje.urlPicture).into(objectImageView)
                }
            }
        }
    }
}