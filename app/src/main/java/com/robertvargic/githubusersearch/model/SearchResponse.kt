package com.robertvargic.githubusersearch.model

import com.google.gson.annotations.SerializedName

typealias Json = SerializedName

data class SearchResponse(@Json("total_count") val totalCount: Int,
                    @Json("incomplete_result") var incompleteResults: Boolean,
                    @Json("items") var items: MutableList<User>) {
}