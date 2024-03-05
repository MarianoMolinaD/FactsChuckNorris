package com.portafoliowebmariano.factschucknorris.di


import com.portafoliowebmariano.factschucknorris.data.network.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    fun providesRetrofit():  Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.chucknorris.io/jokes/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providesApiClient(retrofit: Retrofit): ApiClient{
        return retrofit.create(ApiClient::class.java)
    }

}