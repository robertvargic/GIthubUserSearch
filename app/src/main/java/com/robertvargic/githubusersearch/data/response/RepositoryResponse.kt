package com.robertvargic.githubusersearch.data.response

import com.robertvargic.githubusersearch.data.model.Repository
import com.robertvargic.githubusersearch.util.Json

data class RepositoryResponse(@Json("id") var id: String,
                              var userId: String,
                              var name: String,
                              @Json("full_name") var fullName: String,
                              @Json("html_url") var htmlUrl: String)

fun RepositoryResponse.mapToRepositoryModel(): Repository {
    return Repository(id, userId, name, fullName, htmlUrl)
}