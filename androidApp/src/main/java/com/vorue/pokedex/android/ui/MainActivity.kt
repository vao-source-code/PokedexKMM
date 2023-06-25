package com.vorue.pokedex.android.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.vorue.pokedex.android.ui.viewModel.PokedexViewModel
import com.vorue.pokedex.android.data.network.Pokedex
import com.vorue.pokedex.android.databinding.ActivityMainBinding
import com.vorue.pokedex.android.domain.factory.PokedexScreenState
import com.vorue.pokedex.android.domain.factory.PokedexViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), RecyclerViewInterface.OnItemClickListener {

    private lateinit var pokedexAdapter: PokedexAdapter
    private lateinit var viewModel: PokedexViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        // Esto se debe delegar a un view model que trabaje mejor esto y no se tenga que hacer en el activity
        viewModel = ViewModelProvider(this, PokedexViewModelFactory())[PokedexViewModel::class.java]
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.screenState.collect {
                    when (it) {
                        PokedexScreenState.Loading -> showLoading()
                        PokedexScreenState.Error -> handlerError()
                        is PokedexScreenState.ShowPokedex -> showPokedex(it.pokedex)
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        pokedexAdapter = PokedexAdapter(this)
        val gridLayoutManager = GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false)
        with(binding.rvPokedex) {
            this.layoutManager = gridLayoutManager
            this.setHasFixedSize(true)
            this.adapter = pokedexAdapter
        }
    }

    // Esto se debe delegar a un view model
    private fun showPokedex(pokedex: Pokedex) {
        binding.pokedexProgressBar.visibility = View.GONE
        pokedexAdapter.updatePokedex(pokedex.results)
    }

    // Esto se debe delegar a un view model
    private fun handlerError() {
        Toast.makeText(this, "Error buscando la informacion", Toast.LENGTH_LONG).show()
    }

    // Esto se debe delegar a un view model
    private fun showLoading() {
        binding.pokedexProgressBar.visibility = View.VISIBLE
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, PokemonDetails::class.java)
        intent.putExtra("name", pokedexAdapter.searchPokedex(position.toInt()).name)
        intent.putExtra(
            "id",
            pokedexAdapter.searchPokedex(position).url.split('/')[6].toInt()
        )
        startActivity(intent)
    }
}