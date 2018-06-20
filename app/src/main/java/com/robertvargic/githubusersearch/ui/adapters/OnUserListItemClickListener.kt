package com.robertvargic.githubusersearch.ui.adapters

import com.robertvargic.githubusersearch.data.model.User

interface OnUserListItemClickListener {
    fun onFavouriteClick(user: User)
    fun onClick(userName: String)
}