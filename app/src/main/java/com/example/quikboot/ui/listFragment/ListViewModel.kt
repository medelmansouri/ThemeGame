package com.example.quikboot.ui.listFragment

import androidx.lifecycle.*
import com.example.quikboot.data.models.Game
import com.example.quikboot.data.models.NetworkState
import com.example.quikboot.data.repositories.GameRepository
import kotlinx.coroutines.launch

class ListViewModel constructor(private val personRepository: GameRepository) : ViewModel() {
    private val _gameInfo = MutableLiveData<Game?>()
    val gameInfo: LiveData<Game?>
        get() = _gameInfo

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun getGameInfo() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            when (val response = personRepository.getGameInfo()) {
                is NetworkState.Success -> {
                    _isLoading.postValue(false)
                    _gameInfo.postValue(response.data)
                }
                is NetworkState.Error -> {
                    _isLoading.postValue(false)
                }
            }
        }
    }

    class Factory constructor(private val repository: GameRepository): ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
                ListViewModel(this.repository) as T
            } else {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }
}