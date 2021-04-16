package com.monsalven.practica_2_login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("metod", "onCreate")
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