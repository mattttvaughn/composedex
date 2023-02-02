package io.github.mattttvaughn.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import io.github.mattttvaughn.pokedex.models.Pokemon
import io.github.mattttvaughn.pokedex.ui.theme.PokedexTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainActivity : ComponentActivity() {

    private val pokemonListScreenModule = module {
        single(createdAtStart = true) {
            PokemonDataSource(
                pokemon = listOf(
                    Pokemon("Lampoon"),
                    Pokemon("Turqle"),
                    Pokemon("Lampoon"),
                    Pokemon("Lampoon"),
                    Pokemon("Lampoon"),
                    Pokemon("Lampoon"),
                    Pokemon("Lampoon"),
                    Pokemon("Lampoon"),
                    Pokemon("Lampoon"),
                    Pokemon("Lampoon"),
                    Pokemon("Lampoon"),
                    Pokemon("Lampoon"),
                    Pokemon("Turqle"),
                    Pokemon("Turqle"),
                    Pokemon("Turqle"),
                    Pokemon("Turqle"),
                    Pokemon("Turqle"),
                    Pokemon("Turqle"),
                    Pokemon("Turqle"),
                    Pokemon("Turqle"),
                    Pokemon("Turqle"),
                    Pokemon("Turqle"),
                    Pokemon("Lampoon"),
                    Pokemon("Turqle"),
                    Pokemon("Lampoon"),
                    Pokemon("Turqle"),
                    Pokemon("Lampoon"),
                    Pokemon("Turqle"),
                    Pokemon("Lampoon"),
                    Pokemon("Turqle"),
                    Pokemon("Lampoon"),
                    Pokemon("Turqle"),
                    Pokemon("Lampoon"),
                    Pokemon("Turqle"),
                )
            )
        }
        viewModel { PokedexListViewModel(get(), get()) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Starting koin in activity temporarily to get up to speed faster.
        //       Should refactor to start in custom application in the future.
        startKoin {
            modules(pokemonListScreenModule)
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            PokedexTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScreenPokemonList()
                }
            }
        }
    }
}

@Composable
fun ScreenPokemonList(vm: PokedexListViewModel = koinViewModel()) {
    var textState by remember {
        mutableStateOf(TextFieldValue(""))
    }
    Column(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .background(color = Color.Companion.Blue)
                .padding(24.dp)
        ) {
            Text(
                text = "Who are you looking for?",
                color = Color.White,

            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = textState,
                onValueChange = { textState = it },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    // Indicator colors below color the line drawn along the bottom
                    // of the TextField.
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(28.dp),
                placeholder = { Text("E.g. Pikachu") },
                modifier = Modifier.fillMaxWidth()
            )
        }
        LazyColumn {
            items(vm.pokemon().size) { i ->
                Row {
                    Text(text = vm.pokemon()[i].name)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PokedexTheme {
        ScreenPokemonList(
            PokedexListViewModel(
                pokemonDataSource = PokemonDataSource(
                    listOf(
                        Pokemon("Line #1"),
                        Pokemon("Pikachu"),
                        Pokemon("Line #3")
                    )
                ),
                null,
            )
        )
    }
}