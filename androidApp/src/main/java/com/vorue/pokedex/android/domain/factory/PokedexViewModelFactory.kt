package com.vorue.pokedex.android.domain.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vorue.pokedex.android.core.PokedexClient
import com.vorue.pokedex.android.core.RetrofitHelper
import com.vorue.pokedex.android.domain.PokedexRepositoryImp
import com.vorue.pokedex.android.ui.viewModel.PokedexViewModel

class PokedexViewModelFactory : ViewModelProvider.Factory {

    // Esta muy simple el factory, pero es la forma de crear un ViewModel con un constructor
    // que recibe parametros
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val pokedexClient = RetrofitHelper.getRetrofit().create(PokedexClient::class.java)
        val pokedexRepository = PokedexRepositoryImp(pokedexClient)
        return PokedexViewModel(pokedexRepository) as T
    }
}