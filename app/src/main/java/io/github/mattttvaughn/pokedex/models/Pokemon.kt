package io.github.mattttvaughn.pokedex.models

data class Pokemon(val name: String, val url: String) {
    fun spriteUrl() : String {
        return SPRITE_FRONT_DEFAULT_PATH.replace(TEMPLATE_STRING_PLACEHOLDER, pokemonId().toString())
    }

    fun pokemonNumber() : String {
        val leftPaddedNumber = (pokemonId() ?: 0).toString().padStart(3, '0')
        return "#$leftPaddedNumber"
    }

    private fun pokemonId() : Int? {
        return url.dropLast(1).takeLastWhile { it != '/' }.toIntOrNull()
    }

    companion object {
        private const val TEMPLATE_STRING_PLACEHOLDER = "[TEMPLATE]"
        private const val SPRITE_FRONT_DEFAULT_PATH = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$TEMPLATE_STRING_PLACEHOLDER.png"
    }
}