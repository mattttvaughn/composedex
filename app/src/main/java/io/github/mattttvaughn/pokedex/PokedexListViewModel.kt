package io.github.mattttvaughn.pokedex

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class PokedexListViewModel(
    val pokemonDataSource: PokemonDataSource,
    val handle: SavedStateHandle? = null,
) : ViewModel() {
    fun pokemon() = pokemonDataSource.pokemon
}