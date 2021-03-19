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

            if(!entry.isEmpty()){
                val value: Double? = entry.toDouble()

                when (initialCoin) {
                    getString(R.string.peso_colombiano) -> {
                        if (endCoin == getString(R.string.dolar_estadounidense)) if (value != null) {
                            change = value * 0.00028
                        }
                        else {
                            if (endCoin == getString(R.string.euro)) if (value != null) {
                                change = value * 0.00024
                            }
                            else {
                                if (endCoin == getString(R.string.yen_japones)) if (value != null) {
                                    change = value * 0.031
                                }
                                else {
                                    if (value != null) {
                                        change = value
                                    }
                                }
                            }
                        }
                    }
                    getString(R.string.dolar_estadounidense) -> {
                        if (endCoin == getString(R.string.peso_colombiano)) if (value != null) {
                            change = value * 3557.17
                        }
                        else {
                            if (endCoin == getString(R.string.euro)) if (value != null) {
                                change = value * 0.84
                            }
                            else {
                                if (endCoin == getString(R.string.yen_japones)) if (value != null) {
                                    change = value * 108.90
                                }
                                else {
                                    if (value != null) {
                                        change = value
                                    }
                                }
                            }
                        }
                    }
                    getString(R.string.euro) -> {
                        if (endCoin == getString(R.string.peso_colombiano)) if (value != null) {
                            change = value * 4236.91
                        }
                        else {
                            if (endCoin == getString(R.string.dolar_estadounidense)) if (value != null) {
                                change = value * 1.19
                            }
                            else {
                                if (endCoin == getString(R.string.yen_japones)) if (value != null) {
                                    change = value * 129.68
                                }
                                else {
                                    if (value != null) {
                                        change = value
                                    }
                                }
                            }
                        }
                    }
                    else -> {
                        if (endCoin == getString(R.string.peso_colombiano)) if (value != null) {
                            change = value * 32.68
                        }
                        else {
                            if (endCoin == getString(R.string.dolar_estadounidense)) if (value != null) {
                                change = value * 0.0092
                            }
                            else {
                                if (endCoin == getString(R.string.euro)) if (value != null) {
                                    change = value * 0.0077
                                }
                                else {
                                    if (value != null) {
                                        change = value
                                    }
                                }
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