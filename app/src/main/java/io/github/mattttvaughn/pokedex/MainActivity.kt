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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import io.github.mattttvaughn.pokedex.network.PokedexApiService
import io.github.mattttvaughn.pokedex.ui.theme.PokedexTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

const val BASE_POKEDEX_URL = "https://pokeapi.co/api/v2/"

class MainActivity : ComponentActivity() {

    private val pokemonListScreenModule = module {
        single(createdAtStart = true) {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_POKEDEX_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            retrofit.create(PokedexApiService::class.java)
        }

        single(createdAtStart = true) {
            PokemonRepository(get())
        }
        viewModel { PokedexListViewModel(get(), get()) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Starting koin in activity temporarily to get up to speed faster.
        //       Should refactor to start in custom application in the future.
        initApp()
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

    private fun initApp() {
        startKoin {
            modules(pokemonListScreenModule)
        }
        Timber.plant(Timber.DebugTree())
    }
}

@Composable
fun ScreenPokemonList(vm: PokedexListViewModel = koinViewModel()) {
    val pokemonList by vm.pokemonStateFlow.collectAsState()
    Column(modifier = Modifier.fillMaxWidth()) {
        SearchHeader()
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(pokemonList?.size ?: 0) { i ->
                Row {
                    Text(text = pokemonList?.get(i)?.name.orEmpty())
                }
            }
        }
    }
}

@Composable
private fun SearchHeader() {
    Column(
        modifier = Modifier
            .background(color = Color.Companion.Blue)
            .padding(24.dp)
    ) {
        var textState by remember {
            mutableStateOf(TextFieldValue(""))
        }
        Spacer(modifier = Modifier.statusBarsPadding())
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
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PokedexTheme {
// TODO: figure out how to get fake data in here
//        ScreenPokemonList(
//            PokedexListViewModel(
//                pokemonRepository = PokemonRepository(
//                    PokedexApiService()
//                ),
//                null,
//            )
//        )
    }
}