package com.monsalven.Practica_3_Fragments.model

data class User(
        var id: String? = null,
        var name: String? = null,
        var email: String? = null,
        var phone: String? = null,
        var vinculation: String? = null,
        var ced: String? = null,
        var adminpower: Boolean? = null,
        var urlPicture: String? = null,
)
