package com.monsalven.practica_2_login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.monsalven.practica_2_login.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        Log.d("metod", "onCreate")

        val ttb = AnimationUtils.loadAnimation(this,R.anim.ttb)
        val mainTittleMain = findViewById<TextView>(R.id.main_tittle_main)
        mainTittleMain.startAnimation(ttb)

    }

    override fun onStart() {
        super.onStart()
        Log.d("metod", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("metod", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("metod", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("metod", "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("metod", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("metod", "onDestroy")
    }
}