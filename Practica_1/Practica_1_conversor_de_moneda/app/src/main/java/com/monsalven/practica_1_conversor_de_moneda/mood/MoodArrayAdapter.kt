package com.monsalven.practica_1_conversor_de_moneda.mood

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.monsalven.practica_1_conversor_de_moneda.R
import kotlinx.android.synthetic.main.custom_spinner_item.view.*

class MoodArrayAdapter(ctx: Context, moods: List<Mood>) : ArrayAdapter<Mood>(ctx, 0, moods) {

    override fun getView(position: Int, recycledView: View?, parent: ViewGroup): View {
        return this.createView(position, recycledView, parent)
    }

    override fun getDropDownView(position: Int, recycledView: View?, parent: ViewGroup): View {
        return this.createView(position, recycledView, parent)
    }

    private fun createView(position: Int, recycledView: View?, parent: ViewGroup): View {

        val mood = getItem(position)

        val view = recycledView ?: LayoutInflater.from(context).inflate(
                R.layout.custom_spinner_item,
                parent,
                false
        )

        view.moodImage.setImageResource(mood!!.image)
        view.moodText.text = mood!!.description

        return view
    }
}