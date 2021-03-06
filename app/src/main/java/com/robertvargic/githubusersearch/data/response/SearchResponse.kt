package com.robertvargic.githubusersearch.data.response
import com.robertvargic.githubusersearch.util.Json

data class SearchResponse(@Json("total_count") val totalCount: Int,
                    @Json("incomplete_result") var incompleteResults: Boolean,
                    @Json("items") var items: MutableList<UserResponse>) {
}