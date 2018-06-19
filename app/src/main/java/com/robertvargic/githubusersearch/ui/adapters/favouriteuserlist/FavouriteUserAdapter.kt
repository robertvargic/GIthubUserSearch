package com.robertvargic.githubusersearch.ui.adapters.favouriteuserlist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.robertvargic.githubusersearch.R
import com.robertvargic.githubusersearch.model.User

class FavouriteUserAdapter(private val items: MutableList<User>,
                           private val context: Context?,
                           private val onUserClick: (String) -> Unit) : RecyclerView.Adapter<FavouriteUserHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteUserHolder {
        return FavouriteUserHolder(LayoutInflater.from(context).inflate(R.layout.list_item_favourite_user, parent, false), onUserClick)
    }

    override fun onBindViewHolder(holder: FavouriteUserHolder, position: Int) {
        holder.bindData(items[position])
    }

    override fun getItemCount(): Int = items.size
}