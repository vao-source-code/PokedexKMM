package com.vorue.pokedex.android.ui

interface RecyclerViewInterface {

    interface OnItemClickListener {
        fun onItemClick(position: Int)

    }

    interface OnItemSetFavoriteListener {
        fun onItemSetFavorite(position: Int)
    }


}