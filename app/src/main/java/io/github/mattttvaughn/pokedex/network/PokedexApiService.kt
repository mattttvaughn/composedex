package io.github.mattttvaughn.pokedex.network

import retrofit2.http.GET

interface PokedexApiService {
    @GET("pokemon?limit=100000&offset=0")
    suspend fun fetchAllPokemon(): PokemonResponseWrapper
}