package com.robertvargic.githubusersearch.model

import com.google.gson.annotations.SerializedName

typealias Json = SerializedName

data class Response(@Json("")val totalCount(): String,
                    var userName: String) {
}