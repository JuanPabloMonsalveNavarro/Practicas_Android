package com.monsalven.Practica_3_Fragments.extension

import android.util.Patterns

fun String.isValidEmail(): Boolean = this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()