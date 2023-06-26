package com.vorue.pokedex.android.domain.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vorue.pokedex.android.ui.viewModel.PokedexViewModel

class PokedexViewModelFactory : ViewModelProvider.Factory {

    // Esta muy simple el factory, pero es la forma de crear un ViewModel con un constructor
    // que recibe parametros
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PokedexViewModel() as T
    }
}