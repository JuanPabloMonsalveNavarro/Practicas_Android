package com.monsalven.Practica_3_Fragments.model

import com.google.gson.annotations.SerializedName

data class Object (
    var id: String? = null,
    var name: String? = null,
    var status: String? = null,
    var state: String? = null,
    var urlPicture: String? =null
)