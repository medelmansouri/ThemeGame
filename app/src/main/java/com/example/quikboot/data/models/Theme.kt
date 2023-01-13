package com.example.quikboot.data.models

import com.google.gson.annotations.SerializedName


data class Theme (

  @SerializedName("id"       ) var id       : Int?                = null,
  @SerializedName("label"    ) var label    : String?             = null,
  @SerializedName("question" ) var question : ArrayList<Question> = arrayListOf()

) : java.io.Serializable