package com.monsalven.practica_1_conversor_de_moneda

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.monsalven.practica_1_conversor_de_moneda.databinding.ActivityMainBinding
import com.monsalven.practica_1_conversor_de_moneda.mood.Mood
import com.monsalven.practica_1_conversor_de_moneda.mood.MoodArrayAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.unitSpinner1.adapter = MoodArrayAdapter(this,
                listOf(
                        Mood(R.mipmap.colombia_flag, getString(R.string.peso_colombiano)),
                        Mood(R.mipmap.usa_flag, getString(R.string.dolar_estadounidense)),
                        Mood(R.mipmap.europ_flag, getString(R.string.euro)),
                        Mood(R.mipmap.japan_flag, getString(R.string.yen_japones))
                )
        )

        mainBinding.unitSpinner2.adapter = MoodArrayAdapter(this,
                listOf(
                        Mood(R.mipmap.usa_flag, getString(R.string.dolar_estadounidense)),
                        Mood(R.mipmap.colombia_flag, getString(R.string.peso_colombiano)),
                        Mood(R.mipmap.europ_flag, getString(R.string.euro)),
                        Mood(R.mipmap.japan_flag, getString(R.string.yen_japones))
                )
        )

        mainBinding.saveButton.setOnClickListener {
            val entry       = mainBinding.valueEditText.text.toString()
            val initialCoin = (mainBinding.unitSpinner1.findViewById(R.id.moodText) as TextView).text.toString()
            val endCoin     = (mainBinding.unitSpinner2.findViewById(R.id.moodText) as TextView).text.toString()
            var change      = 0.0
            var value = 0.0

            if(!entry.isEmpty()){
                value = entry.toDouble()

                when (initialCoin) {
                    getString(R.string.peso_colombiano) -> {
                        when(endCoin){
                            getString(R.string.dolar_estadounidense) -> {
                                change = value * 0.00027
                            }

                            getString(R.string.euro) ->{
                                change = value * 0.00023
                            }

                            getString(R.string.yen_japones) ->{
                                change = value * 0.030
                            }

                            getString(R.string.peso_colombiano) ->{
                                change = value * 1
                            }

                        }
                    }
                    getString(R.string.dolar_estadounidense) -> {
                        when(endCoin) {
                            getString(R.string.euro) -> {
                                change = value * 0.85
                            }

                            getString(R.string.yen_japones) -> {
                                change = value * 110.71
                            }

                            getString(R.string.peso_colombiano) -> {
                                change = value * 3661.70
                            }

                            getString(R.string.dolar_estadounidense) -> {
                                change = value * 1
                            }

                        }
                    }
                    getString(R.string.euro) -> {
                        when(endCoin){
                            getString(R.string.dolar_estadounidense) -> {
                                change = value * 1.18
                            }

                            getString(R.string.euro) ->{
                                change = value * 1
                            }

                            getString(R.string.yen_japones) ->{
                                change = value * 130.21
                            }

                            getString(R.string.peso_colombiano) ->{
                                change = value * 4306.84
                            }

                        }
                    }

                    getString(R.string.yen_japones) -> {
                        when(endCoin){
                            getString(R.string.dolar_estadounidense) -> {
                                change = value * 0.0090
                            }

                            getString(R.string.euro) ->{
                                change = value * 0.0077
                            }

                            getString(R.string.yen_japones) ->{
                                change = value * 1
                            }

                            getString(R.string.peso_colombiano) ->{
                                change = value * 33.08
                            }

                        }
                    }
                }

                var data = "Valor ingresado $ " + value  + " " + initialCoin +
                           "\n\nConversión $ "  + change + " " + endCoin
                mainBinding.printResultTextView.text = data
                mainBinding.valueTextInputLayout.error = null
                mainBinding.valueEditText.text = null

            } else {
                mainBinding.valueTextInputLayout.error = getString(R.string.value_error)
            }
        }
    }
}