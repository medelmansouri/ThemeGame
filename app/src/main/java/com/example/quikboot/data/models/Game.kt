package com.example.quikboot.data.models

import com.google.gson.annotations.SerializedName


data class Game (

  @SerializedName("theme" ) var theme : ArrayList<Theme> = arrayListOf(),
  @SerializedName("error" ) var error : String?          = null

)