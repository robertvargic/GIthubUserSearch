package com.robertvargic.githubusersearch.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robertvargic.githubusersearch.R
import com.robertvargic.githubusersearch.data.model.User
import kotlinx.android.synthetic.main.list_item_favourite_user.view.*

const val trigger = 4

class FavouriteUserAdapter(
        private val onClick: (String) -> Unit) :
        RecyclerView.Adapter<ViewHolder>() {

    private val items = mutableListOf<User>()

    private var totalItems = 789

    fun setData(users: List<User>) {
        items.clear()
        items.addAll(users)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_favourite_user, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(items[position], onClick)

        if (items.size < totalItems && items.size - trigger == position) {
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindData(user: User, onClick: (String) -> Unit) {
        itemView.userName.text = user.userName
        itemView.numberOfRepos.text = user.numberOfPublicRepos
        itemView.setOnClickListener { onClick(user.id) }

    }
}