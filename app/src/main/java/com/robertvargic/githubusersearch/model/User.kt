package com.robertvargic.githubusersearch.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "userTable")
data class User(@PrimaryKey @ColumnInfo(name = "id") val id: String,
                @Json("login") @ColumnInfo (name = "userName") var userName: String) {
}

