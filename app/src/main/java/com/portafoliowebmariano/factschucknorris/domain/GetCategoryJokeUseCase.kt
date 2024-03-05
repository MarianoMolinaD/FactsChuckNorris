package com.portafoliowebmariano.factschucknorris.domain

import com.portafoliowebmariano.factschucknorris.data.JokesRepository
import com.portafoliowebmariano.factschucknorris.data.model.JokesCategoryData
import javax.inject.Inject

class GetCategoryJokeUseCase @Inject constructor(
    private val repository: JokesRepository
) {

    suspend operator fun invoke(): List<String> = repository.getCategoryJokes()}