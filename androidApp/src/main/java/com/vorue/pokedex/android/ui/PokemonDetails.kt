package com.vorue.pokedex.android.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.vorue.pokedex.android.ui.viewModel.PokemonViewModel
import com.vorue.pokedex.android.R
import com.vorue.pokedex.data.network.PokedexResults
import com.vorue.pokedex.android.libraries.ImageBuilder
import com.vorue.pokedex.android.databinding.ActivityPokemonDetailsBinding

class PokemonDetails : AppCompatActivity() {

    private lateinit var activityPokemonDetailsBinding: ActivityPokemonDetailsBinding

    private lateinit var pokemon: PokedexResults

    private val pokemonViewModel : PokemonViewModel by viewModels()

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityPokemonDetailsBinding = ActivityPokemonDetailsBinding.inflate(layoutInflater)
        setContentView(activityPokemonDetailsBinding.root)
        val name = intent.extras!!.getString("name")!!
        val id = intent.extras!!.getInt("id")

         pokemon = PokedexResults(id = id, name = name, url = "https://pokeapi.co/api/v2/pokemon/$id/")

        initPokemon(name, id)

        initImage()

    }

    private fun initPokemon(valor: String, id: Int) {
        //Buscar el pokemon y traer todos los datos
        pokemonViewModel.setPokemon(id)

        pokemonViewModel.pokemon.observe(this) { pokemon ->
            this.pokemon = pokemon
            activityPokemonDetailsBinding.textNamePokemon.text = pokemon.name
           // activityPokemonDetailsBinding.textAbility.text = pokemon.abilities.forEach { it.ability!!.name }.toString()
        }

    }

    private fun initImage() {
        activityPokemonDetailsBinding.textNamePokemon.text = pokemon.name

        Glide.with(this)
            .load(ImageBuilder.buildPokemonImageByUrl(pokemon.url))
            .placeholder(R.drawable.pokeball)
            .error(R.drawable.pokeball) // cambiar icono
            .into(activityPokemonDetailsBinding.imagePokemon)

    }
}