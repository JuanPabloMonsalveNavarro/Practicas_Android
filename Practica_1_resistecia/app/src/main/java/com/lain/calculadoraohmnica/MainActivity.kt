package com.lain.calculadoraohmnica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lain.calculadoraohmnica.databinding.ActivityMainBinding
import com.lain.calculadoraohmnica.mood.Mood
import com.lain.calculadoraohmnica.mood.MoodArrayAdapter



class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.banda1Spinner.adapter = MoodArrayAdapter(this,
            listOf(
                    Mood(R.mipmap.amarillo_foreground, "Amarillo")
            )
        )

    }
}