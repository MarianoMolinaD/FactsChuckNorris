package com.portafoliowebmariano.factschucknorris.data.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JokeProvider @Inject constructor() {
    var currentJoke: JokesRandomData? = null
    var JokeCategory: List<String> = emptyList()
}