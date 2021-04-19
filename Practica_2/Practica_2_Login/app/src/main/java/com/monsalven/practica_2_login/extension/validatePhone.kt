package com.monsalven.practica_2_login.extension

import android.util.Patterns

fun String.isValidPhone(): Boolean
        = this.isNotEmpty() && Patterns.PHONE.matcher(this).matches()