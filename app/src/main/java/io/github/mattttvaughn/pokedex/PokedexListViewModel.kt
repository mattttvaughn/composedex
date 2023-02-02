package io.github.mattttvaughn.pokedex

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PokedexListViewModel(
    val pokemonRepository: PokemonRepository,
    val handle: SavedStateHandle? = null,
) : ViewModel() {

    init {
        viewModelScope.launch {
            pokemonRepository.fetchPokemon()
        }
    }

    // TODO: Loading/error state
    val pokemonStateFlow = pokemonRepository.pokemon


}