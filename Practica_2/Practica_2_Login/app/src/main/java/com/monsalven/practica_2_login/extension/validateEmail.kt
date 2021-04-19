package com.monsalven.practica_2_login.extension

import android.util.Patterns

fun String.isValidEmail(): Boolean
        = this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()