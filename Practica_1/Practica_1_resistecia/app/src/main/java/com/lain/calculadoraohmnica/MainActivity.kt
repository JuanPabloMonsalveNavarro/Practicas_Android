package com.lain.calculadoraohmnica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.lain.calculadoraohmnica.databinding.ActivityMainBinding
import com.lain.calculadoraohmnica.mood.Mood
import com.lain.calculadoraohmnica.mood.MoodArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.banda1Spinner.adapter = MoodArrayAdapter(this,
            listOf(
                    Mood(R.mipmap.amarillo_foreground, getString(R.string.amarillo_string)),
                    Mood(R.mipmap.azul_foreground, getString(R.string.azul_string)),
                    Mood(R.mipmap.blanco_foreground,getString(R.string.blanco_string)),
                    Mood(R.mipmap.gris_foreground,getString(R.string.gris_string)),
                    Mood(R.mipmap.violeta_foreground,getString(R.string.morado_string)),
                    Mood(R.mipmap.marron_foreground,getString(R.string.cafe_string)),
                    Mood(R.mipmap.naranja_foreground,getString(R.string.naranja_string)),
                    Mood(R.mipmap.rojo_foreground,getString(R.string.rojo_string)),
                    Mood(R.mipmap.negro_foreground,getString(R.string.negro_string)),
                    Mood(R.mipmap.verde_foreground,getString(R.string.verde_string))

            )
        )

        mainBinding.banda2Spinner.adapter = MoodArrayAdapter(this,
            listOf(
                    Mood(R.mipmap.amarillo_foreground, getString(R.string.amarillo_string)),
                    Mood(R.mipmap.azul_foreground, getString(R.string.azul_string)),
                    Mood(R.mipmap.blanco_foreground,getString(R.string.blanco_string)),
                    Mood(R.mipmap.gris_foreground,getString(R.string.gris_string)),
                    Mood(R.mipmap.violeta_foreground,getString(R.string.morado_string)),
                    Mood(R.mipmap.marron_foreground,getString(R.string.cafe_string)),
                    Mood(R.mipmap.naranja_foreground,getString(R.string.naranja_string)),
                    Mood(R.mipmap.rojo_foreground,getString(R.string.rojo_string)),
                    Mood(R.mipmap.negro_foreground,getString(R.string.negro_string)),
                    Mood(R.mipmap.verde_foreground,getString(R.string.verde_string))
            )
        )

        mainBinding.multiplicadorSpinner.adapter = MoodArrayAdapter(this,
            listOf(
                    Mood(R.mipmap.amarillo_foreground, getString(R.string.amarillo_string)),
                    Mood(R.mipmap.azul_foreground, getString(R.string.azul_string)),
                    Mood(R.mipmap.blanco_foreground,getString(R.string.blanco_string)),
                    Mood(R.mipmap.gris_foreground,getString(R.string.gris_string)),
                    Mood(R.mipmap.violeta_foreground,getString(R.string.morado_string)),
                    Mood(R.mipmap.marron_foreground,getString(R.string.cafe_string)),
                    Mood(R.mipmap.naranja_foreground,getString(R.string.naranja_string)),
                    Mood(R.mipmap.rojo_foreground,getString(R.string.rojo_string)),
                    Mood(R.mipmap.negro_foreground,getString(R.string.negro_string)),
                    Mood(R.mipmap.verde_foreground,getString(R.string.verde_string))
            )
        )

        mainBinding.toleranciaSpinner.adapter = MoodArrayAdapter(this,
            listOf(
                    Mood(R.mipmap.amarillo_foreground, getString(R.string.amarillo_string)),
                    Mood(R.mipmap.gris_foreground,getString(R.string.gris_string))
            )
        )

        mainBinding.calcularButton.setOnClickListener {
            var banda1_int = 0
            var banda2_int = 0
            var multiplicador_int = 0
            var tolerancia_int = 0
            var final_value_int = 0

            val banda2_value        = (mainBinding.banda2Spinner.findViewById(R.id.moodText) as TextView).text.toString()
            val multiplicador_value = (mainBinding.multiplicadorSpinner.findViewById(R.id.moodText) as TextView).text.toString()
            val tolerancia_value    = (mainBinding.toleranciaSpinner.findViewById(R.id.moodText) as TextView).text.toString()
            val banda1_value        = (mainBinding.banda1Spinner.findViewById(R.id.moodText) as TextView).text.toString()

            when(banda1_value){
                getString(R.string.negro_string)    -> {banda1_int = 0}
                getString(R.string.cafe_string)     -> {banda1_int = 1}
                getString(R.string.rojo_string)     -> {banda1_int = 2}
                getString(R.string.naranja_string)  -> {banda1_int = 3}
                getString(R.string.amarillo_string) -> {banda1_int = 4}
                getString(R.string.verde_string)    -> {banda1_int = 5}
                getString(R.string.azul_string)     -> {banda1_int = 6}
                getString(R.string.morado_string)   -> {banda1_int = 7}
                getString(R.string.gris_string)     -> {banda1_int = 8}
                getString(R.string.blanco_string)   -> {banda1_int = 9}
            }

            when(banda2_value){
                getString(R.string.negro_string)    -> {banda2_int = 0}
                getString(R.string.cafe_string)     -> {banda2_int = 1}
                getString(R.string.rojo_string)     -> {banda2_int = 2}
                getString(R.string.naranja_string)  -> {banda2_int = 3}
                getString(R.string.amarillo_string) -> {banda2_int = 4}
                getString(R.string.verde_string)    -> {banda2_int = 5}
                getString(R.string.azul_string)     -> {banda2_int = 6}
                getString(R.string.morado_string)   -> {banda2_int = 7}
                getString(R.string.gris_string)     -> {banda2_int = 8}
                getString(R.string.blanco_string)   -> {banda2_int = 9}
            }

            when(multiplicador_value){
                getString(R.string.negro_string)    -> {multiplicador_int = 1}
                getString(R.string.cafe_string)     -> {multiplicador_int = 10}
                getString(R.string.rojo_string)     -> {multiplicador_int = 100}
                getString(R.string.naranja_string)  -> {multiplicador_int = 10000}
                getString(R.string.amarillo_string) -> {multiplicador_int = 100000}
                getString(R.string.verde_string)    -> {multiplicador_int = 1000000}
                getString(R.string.azul_string)     -> {multiplicador_int = 10000000}
                getString(R.string.morado_string)   -> {multiplicador_int = 100000000}
                getString(R.string.gris_string)     -> {multiplicador_int = 1000000000}
                getString(R.string.blanco_string)   -> {multiplicador_int = 10000000000.toInt()}
            }

            when(tolerancia_value){
                getString(R.string.amarillo_string) -> {tolerancia_int = 5}
                getString(R.string.gris_string) -> {tolerancia_int = 10}
            }

            final_value_int = ((banda1_int*10) + banda2_int)*multiplicador_int
            var data = "$final_value_int ± $tolerancia_int %"
            mainBinding.resultado.text = data
            }
        }
    }
