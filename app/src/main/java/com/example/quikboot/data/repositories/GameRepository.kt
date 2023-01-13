package com.example.quikboot.data.repositories

import com.example.quikboot.data.datasources.ApiService
import com.example.quikboot.data.models.Game
import com.example.quikboot.data.models.NetworkState

class GameRepository constructor(private val retrofitService: ApiService) {

    suspend fun getGameInfo() : NetworkState<Game> {
        val response = retrofitService.getGameInfo()
        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                NetworkState.Success(responseBody)
            } else {
                NetworkState.Error(response)
            }
        } else {
            NetworkState.Error(response)
        }
    }

}