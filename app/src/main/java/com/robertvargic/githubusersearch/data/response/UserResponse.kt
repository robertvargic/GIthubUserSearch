package com.robertvargic.githubusersearch.data.response

import com.robertvargic.githubusersearch.data.model.User
import com.robertvargic.githubusersearch.util.Json

data class UserResponse(val id: String,
                        @Json("login") var userName: String, //used for getting data for user from endpoints
                        @Json("avatar_url") val avatarUrl: String?,
                        @Json("html_url") val url: String?,
                        @Json("name") val name: String?,
                        val bio: String?,
                        @Json("public_repos") val numberOfPublicRepos: String?)

fun UserResponse.mapToUserModel(): User {
    return User(id, userName, avatarUrl, url, name, bio, numberOfPublicRepos)
}