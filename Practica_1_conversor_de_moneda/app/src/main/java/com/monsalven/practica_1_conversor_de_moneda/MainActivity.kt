package com.monsalven.practica_1_conversor_de_moneda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.monsalven.practica_1_conversor_de_moneda.databinding.ActivityMainBinding
import com.monsalven.practica_1_conversor_de_moneda.mood.Mood
import com.monsalven.practica_1_conversor_de_moneda.mood.MoodArrayAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.unitSpinner1.adapter = MoodArrayAdapter(
                this,
                listOf(
                        Mood(R.mipmap.colombia_flag, getString(R.string.peso_colombiano)),
                        Mood(R.mipmap.usa_flag, getString(R.string.dolar_estadounidense)),
                        Mood(R.mipmap.europ_flag, getString(R.string.euro)),
                        Mood(R.mipmap.japan_flag, getString(R.string.yen_japones))
                )
        )

        mainBinding.unitSpinner2.adapter = MoodArrayAdapter(
                this,
                listOf(
                        Mood(R.mipmap.colombia_flag, getString(R.string.peso_colombiano)),
                        Mood(R.mipmap.usa_flag, getString(R.string.dolar_estadounidense)),
                        Mood(R.mipmap.europ_flag, getString(R.string.euro)),
                        Mood(R.mipmap.japan_flag, getString(R.string.yen_japones))
                )
        )
    }
}