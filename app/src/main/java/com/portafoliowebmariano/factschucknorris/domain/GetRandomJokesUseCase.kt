package com.portafoliowebmariano.factschucknorris.domain

import com.portafoliowebmariano.factschucknorris.data.JokesRepository
import com.portafoliowebmariano.factschucknorris.data.model.JokesRandomData
import javax.inject.Inject

class GetRandomJokesUseCase @Inject constructor(
    private val repository: JokesRepository
) {

    suspend operator fun invoke(category: String): JokesRandomData? = repository.getRandomJokes(category)
}