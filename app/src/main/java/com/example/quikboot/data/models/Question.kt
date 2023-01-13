package com.example.quikboot.data.models

import com.google.gson.annotations.SerializedName


data class Question (

  @SerializedName("label"  ) var label  : String?           = null,
  @SerializedName("answer" ) var answer : ArrayList<Answer> = arrayListOf()

)