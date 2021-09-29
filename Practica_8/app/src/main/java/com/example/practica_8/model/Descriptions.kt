package com.example.practica_8.model


import com.google.gson.annotations.SerializedName

data class Descriptions(
    @SerializedName("en")
    val en: String?,
    @SerializedName("it")
    val `it`: String?
)