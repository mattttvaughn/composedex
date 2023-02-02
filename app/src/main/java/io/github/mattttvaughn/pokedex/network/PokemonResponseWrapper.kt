package io.github.mattttvaughn.pokedex.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonResponseWrapper(
    val count: Int,
    val results: List<PokemonResponse>
)

@JsonClass(generateAdapter = true)
data class PokemonResponse(
    val name: String,
    val url: String
)
