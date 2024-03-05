package com.portafoliowebmariano.factschucknorris.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.portafoliowebmariano.factschucknorris.data.model.JokesCategoryData
import com.portafoliowebmariano.factschucknorris.data.model.JokesRandomData
import com.portafoliowebmariano.factschucknorris.domain.GetCategoryJokeUseCase
import com.portafoliowebmariano.factschucknorris.domain.GetRandomJokesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokeViewModel @Inject constructor(
    private val getRandomJokesUseCase: GetRandomJokesUseCase,
    private val getCategoryJokeUseCase: GetCategoryJokeUseCase
) : ViewModel() {

    val currentJoke = MutableLiveData<JokesRandomData?>()
    val category = MutableLiveData<List<String>>()
    val isLoading = MutableLiveData(false)

    @SuppressLint("SuspiciousIndentation")
    fun getRandomJoke(category: String) {
        isLoading.postValue(true)
        viewModelScope.launch {
            val result = getRandomJokesUseCase(category)
            currentJoke.postValue(result)
            isLoading.postValue(false)
        }
    }

    fun getCategory() {
        viewModelScope.launch {
            val result = getCategoryJokeUseCase()
            category.postValue(result)
        }
    }
}