package com.vorue.pokedex.android.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.vorue.pokedex.android.R
import com.vorue.pokedex.android.databinding.ActivityMainBinding
import com.vorue.pokedex.android.domain.factory.PokedexScreenState
import com.vorue.pokedex.android.domain.factory.PokedexViewModelFactory
import com.vorue.pokedex.android.ui.viewModel.PokedexViewModel
import com.vorue.pokedex.data.network.Pokedex
import com.vorue.pokedex.data.network.PokedexResults
import com.vorue.pokedex.libraries.KMMStorage
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), RecyclerViewInterface.OnItemClickListener,
    RecyclerViewInterface.OnItemSetFavoriteListener {

    /*------------------- Variables --------------------------------------------------------------*/
    private lateinit var pokedexAdapter: PokedexAdapter
    private lateinit var viewModel: PokedexViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var searchMenuItem: MenuItem
    private var searchText: String? = null
    private var searchView: SearchView? = null
    private lateinit var menuItemFavorites: MenuItem
    var kmmStorage = KMMStorage(this)


    /*------------------- Lyfe Cycle  ------------------------------------------------------------*/
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

    override fun onRestart() {
        super.onRestart()
        searchView!!.setQuery("", false)
        searchView!!.clearFocus()
        searchView!!.onActionViewCollapsed()
    }


    /*------------------- Recycler ----------------------------------------------------------------*/

    private fun setupRecyclerView() {
        pokedexAdapter = PokedexAdapter(this, this)
        val gridLayoutManager = GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false)
        with(binding.rvPokedex) {
            this.layoutManager = gridLayoutManager
            this.setHasFixedSize(true)
            this.adapter = pokedexAdapter
        }
    }


    /*------------------- Override Method -------------------------------------------------------*/
    override fun onItemSetFavorite(position: Int) {
        pokedexAdapter.setFavorite(position)
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

    /*------------------- Status Recycler View ---------------------------------------------------*/

    private fun showPokedex(pokedex: Pokedex) {
        binding.pokedexProgressBar.visibility = View.GONE
        pokedexAdapter.initPokedex(pokedex.results)
        if (kmmStorage.getBoolean(PokedexViewModel.favorites)) {
            pokedexAdapter.filterFavoritesPokedex()
        }
    }

    private fun handlerError() {
        Toast.makeText(this, "Error buscando la informacion", Toast.LENGTH_LONG).show()
    }

    private fun showLoading() {
        binding.pokedexProgressBar.visibility = View.VISIBLE
    }

    /*------------------- Menu Options -----------------------------------------------------------*/
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_pokedex_main, menu)
        searchMenuItem = menu.findItem(R.id.action_search)
        searchView = searchMenuItem!!.actionView as SearchView
        searchView!!.queryHint = "Search Pokemon"
        searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchText = query
                searchView!!.clearFocus()
                searchView!!.onActionViewCollapsed()
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchText = newText
                if (searchText!!.length >= 2) {
                    val auxList: ArrayList<PokedexResults> =
                        ArrayList<PokedexResults>()
                    for (each in pokedexAdapter.getPokemonList() as ArrayList<PokedexResults>) {

                        if (each.name.toLowerCase()
                                .contains(searchText.toString().toLowerCase())
                        ) auxList.add(each)
                    }
                    pokedexAdapter.updatePokedex(auxList)
                } else {
                    pokedexAdapter.updatePokedex(null)
                }
                return false
            }
        })

        menuItemFavorites = menu.findItem(R.id.filter_by_favorites)
        menuItemFavorites.isChecked = kmmStorage.getBoolean(PokedexViewModel.favorites)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val actions: MutableMap<Int, Runnable> = HashMap()
        actions[R.id.filter_by_favorites] = Runnable { this.menuFilterFavorites() }

        val action = actions[item.itemId]
        action?.run()
        return super.onOptionsItemSelected(item)
    }

    private fun menuFilterFavorites() {
        val newValue = !menuItemFavorites.isChecked
        kmmStorage.putBoolean(PokedexViewModel.favorites, newValue)
        menuItemFavorites.isChecked = newValue
        if (newValue) {
            pokedexAdapter.filterFavoritesPokedex()

        } else {
            pokedexAdapter.updatePokedex(pokedexAdapter.pokemonListOrigin)
        }
    }
}