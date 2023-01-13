package com.example.quikboot.data.datasources

import com.example.quikboot.BuildConfig
import com.example.quikboot.data.models.Game
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("369a0b8f-1b07-49cb-9119-74793f8926dd")
    suspend fun getGameInfo() : Response<Game>

    companion object {
        var apiDataSource: ApiService? = null
        fun getInstance() : ApiService {
            if (apiDataSource == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                apiDataSource = retrofit.create(ApiService::class.java)
            }
            return apiDataSource!!
        }
    }
}