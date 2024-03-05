package com.portafoliowebmariano.factschucknorris.data.network

import com.portafoliowebmariano.factschucknorris.core.RetrofitHelper
import com.portafoliowebmariano.factschucknorris.data.model.JokesCategoryData
import com.portafoliowebmariano.factschucknorris.data.model.JokesRandomData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class JokesServices @Inject constructor(
    private val api : ApiClient
) {
    suspend fun getJokesRandom(category: String): JokesRandomData{
        return withContext(Dispatchers.IO){
            val response = api.getJokesRandom(category)
            response.body()!!
        }
    }

    suspend fun getJokesCategory():List<String>{
         return withContext(Dispatchers.IO){
             val response = api.getJokesCategory()
             response.body()!!
         }
    }
}