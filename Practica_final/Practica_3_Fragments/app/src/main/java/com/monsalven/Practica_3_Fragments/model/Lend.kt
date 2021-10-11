package com.monsalven.Practica_3_Fragments.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Lend (
    @SerializedName("_id")
    var id: String? = null,
    @SerializedName("id_Objeto")
    var idObjeto: String? = null,
    @SerializedName("na_me")
    var name: String? = null,
    @SerializedName("sta_tus")
    var status: String? = null,
    @SerializedName("id__lender")
    var id_lender: String? = null,
    @SerializedName("start__time")
    var start_time: String? = null,
    @SerializedName("finish__time")
    var finish_time: String? = null,
    @SerializedName("url_Picture")
    var urlPicture: String? =null
): Serializable