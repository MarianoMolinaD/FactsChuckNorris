package com.portafoliowebmariano.factschucknorris.data

import com.portafoliowebmariano.factschucknorris.data.model.JokeProvider
import com.portafoliowebmariano.factschucknorris.data.model.JokesCategoryData
import com.portafoliowebmariano.factschucknorris.data.model.JokesRandomData
import com.portafoliowebmariano.factschucknorris.data.network.JokesServices
import javax.inject.Inject

class JokesRepository @Inject constructor(
    private val api : JokesServices,
    private val provider: JokeProvider
) {
    suspend fun getRandomJokes(category : String):JokesRandomData{
        val response : JokesRandomData = api.getJokesRandom(category)
        provider.currentJoke = response
        return response
    }

    suspend fun getCategoryJokes(): List<String> {
        val response : List<String> = api.getJokesCategory()
        provider.JokeCategory = response
        return response
    }
}