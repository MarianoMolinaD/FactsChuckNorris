package com.portafoliowebmariano.factschucknorris.data.network

import com.portafoliowebmariano.factschucknorris.data.model.JokesCategoryData
import com.portafoliowebmariano.factschucknorris.data.model.JokesRandomData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiClient {
    @GET
    suspend fun getJokesRandom(@Url url: String): Response<JokesRandomData>

    @GET("categories")
    suspend fun getJokesCategory(): Response<List<String>>
}