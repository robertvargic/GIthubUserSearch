package com.robertvargic.githubusersearch.ui.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robertvargic.githubusersearch.R
import com.robertvargic.githubusersearch.data.model.User
import kotlinx.android.synthetic.main.list_item_user.view.*

class UserListAdapter(private val items: List<User>, private val context: Context?, private val onSearchUserClickListener: OnUserListItemClickListener?) :
        RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_user, parent, false), onSearchUserClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(view: View, private val listener: OnUserListItemClickListener?) : RecyclerView.ViewHolder(view) {
        fun bindData(user: User) {
            itemView.userName.text = user.userName
            if (listener != null) {
                itemView.favouriteButton.setOnClickListener { listener.onFavouriteClick(user) }
            }
            itemView.setOnClickListener { listener!!.onClick(user.userName) }
        }
    }
}