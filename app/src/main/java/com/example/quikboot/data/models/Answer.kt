package com.example.quikboot.data.models

import com.google.gson.annotations.SerializedName


data class Answer (

  @SerializedName("label"   ) var label   : String?  = null,
  @SerializedName("correct" ) var correct : Boolean? = null,
  var isChecked : Boolean = false

)