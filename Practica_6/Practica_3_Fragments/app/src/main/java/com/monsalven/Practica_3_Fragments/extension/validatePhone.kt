package com.monsalven.Practica_3_Fragments.extension

import android.util.Patterns

fun String.isValidPhone(): Boolean = this.isNotEmpty() && Patterns.PHONE.matcher(this).matches()