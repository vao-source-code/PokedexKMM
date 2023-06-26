package com.vorue.pokedex.android.ui

interface RecyclerViewInterface {

    interface OnItemClickListener {
        fun onItemClick(position: Int)

    }

    interface onItemSetFavoriteListener {
        fun onItemSetFavorite(position: Int)
    }


}