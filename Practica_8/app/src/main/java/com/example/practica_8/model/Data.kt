package com.example.practica_8.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("current_page")
    val currentPage: Int?,
    @SerializedName("documents")
    val documents: List<Document>?,
    @SerializedName("last_page")
    val lastPage: Int?
)