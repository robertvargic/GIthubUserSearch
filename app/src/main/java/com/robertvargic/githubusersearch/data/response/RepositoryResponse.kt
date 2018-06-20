package com.robertvargic.githubusersearch.data.response

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.PrimaryKey
import com.robertvargic.githubusersearch.util.Json

data class RepositoryResponse (@PrimaryKey @Json("id") @ColumnInfo(name = "id") var id: String,
//                      @Embedded @ColumnInfo(name = "userField") var user: User,
                          @ColumnInfo(name = "userId") var userId: String,
                          @ColumnInfo(name = "name") var name: String,
                          @Json("full_name") @ColumnInfo(name = "full_name") var fullName: String,
                          @Json("html_url") @ColumnInfo(name ="html_url") var htmlUrl: String)