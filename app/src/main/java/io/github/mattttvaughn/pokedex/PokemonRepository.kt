package io.github.mattttvaughn.pokedex

import io.github.mattttvaughn.pokedex.models.Pokemon
import io.github.mattttvaughn.pokedex.network.PokedexApiService
import io.github.mattttvaughn.pokedex.network.PokemonResponse
import io.github.mattttvaughn.pokedex.network.PokemonResponseWrapper
import kotlinx.coroutines.flow.MutableStateFlow
import timber.log.Timber

class PokemonRepository(
    private val pokedexApiService: PokedexApiService
) {

    val pokemon = MutableStateFlow<List<Pokemon>?>(null)

    suspend fun fetchPokemon() {
        try {
            val pokemonResponses = pokedexApiService.fetchAllPokemon()
            pokemon.value = pokemonResponses.toPokemons()
        } catch(t: Throwable) {
            // TODO: handle errors
            Timber.e(t)
        }
    }

    private fun PokemonResponseWrapper.toPokemons() : List<Pokemon> {
        return results.map {
            it.asPokemon()
        }
    }

    private fun PokemonResponse.asPokemon() : Pokemon {
        return Pokemon(name = name, url = url )
    }
}