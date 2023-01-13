package com.example.quikboot.ui

import android.util.Log
import androidx.lifecycle.*
import com.example.quikboot.data.models.Game
import com.example.quikboot.data.models.NetworkState
import com.example.quikboot.data.models.Question
import com.example.quikboot.data.models.Theme
import com.example.quikboot.data.repositories.GameRepository
import kotlinx.coroutines.launch

class SharedViewModel  : ViewModel() {

    var questionListWithAnswers = mutableListOf<Question>()

    fun calculateResult(): String{
        return (questionListWithAnswers.flatMap { it.answer }.filter { it.isChecked && it.correct == true }.size * 5).toString()
    }
}