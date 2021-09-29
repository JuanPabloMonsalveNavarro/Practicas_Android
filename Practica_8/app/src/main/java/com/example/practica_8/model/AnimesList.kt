package com.example.practica_8.model


import com.google.gson.annotations.SerializedName

data class AnimesList(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status_code")
    val statusCode: Int?,
    @SerializedName("version")
    val version: String?
)