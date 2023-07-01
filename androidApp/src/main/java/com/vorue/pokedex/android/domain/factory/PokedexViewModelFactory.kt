package com.vorue.pokedex.android.domain.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vorue.pokedex.android.ui.viewModel.PokedexViewModel

class PokedexViewModelFactory(private val context : Context) : ViewModelProvider.Factory {

    // Esta muy simple el factory, pero es la forma de crear un ViewModel con un constructor
    // que recibe parametros
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PokedexViewModel(context) as T
    }
}