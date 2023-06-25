package com.vorue.pokedex.android.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vorue.pokedex.android.R
import com.vorue.pokedex.data.network.PokedexResults
import com.vorue.pokedex.android.databinding.ItemPokedexBinding
import com.vorue.pokedex.android.libraries.ImageBuilder
import com.vorue.pokedex.android.libraries.StringFormatter

class PokedexAdapter( val  onclick: RecyclerViewInterface.OnItemClickListener) :
    RecyclerView.Adapter<PokedexAdapter.PokedexViewHolder>() {

    private var pokemonList = mutableListOf<PokedexResults>()
    private val pokemonListOrigin = mutableListOf<PokedexResults>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexViewHolder {
        val pokedexBinding =
            ItemPokedexBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokedexViewHolder(pokedexBinding)
    }

    override fun onBindViewHolder(holder: PokedexViewHolder, position: Int) {
        val pokemon = pokemonList[position]


        holder.binding.iconImageView.setOnClickListener {
            onclick.onItemClick(position)
        }
        PokedexViewHolder(holder.binding).bind(pokemon)

    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    fun getPokemonList(): List<PokedexResults> {
        return pokemonList
    }

    fun initPokedex(results: List<PokedexResults>?) {
        if (results != null) {
            pokemonListOrigin.addAll(results)
            pokemonList.clear()
            pokemonList.addAll(results)
        }
        notifyDataSetChanged()
    }

    fun updatePokedex(results: List<PokedexResults>?) {
        if (results != null) {
            pokemonList.clear()
            pokemonList.addAll(results)
        }else{
            pokemonList.clear()
            pokemonList.addAll(pokemonListOrigin)
        }
        notifyDataSetChanged()
    }

    fun searchPokedex(id: Int): PokedexResults {
        return pokemonList[id]
    }


    // mejorar view holder
    class PokedexViewHolder(
        val binding: ItemPokedexBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: PokedexResults) {
            binding.titleTextView.text =
                StringFormatter.changeFirstLetterToUppercaseAndDeleteMiddleDash(pokemon.name)
            Glide.with(this.itemView)
                .load(ImageBuilder.buildPokemonImageByUrl(pokemon.url))
                .placeholder(R.drawable.pokeball)
                .error(R.drawable.pokeball) // cambiar icono
                .into(binding.iconImageView)

        }


    }
}