package com.robertvargic.githubusersearch.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

//?@Entity(tableName = "repository") //foreignKeys = @ForeignKey(entity = User.class)
@Entity(tableName = "repository", foreignKeys = [(ForeignKey(entity = User::class, parentColumns = arrayOf("id"), childColumns = arrayOf("owner"), onDelete = ForeignKey.CASCADE))])

data class Repository(@PrimaryKey @Json("id") @ColumnInfo(name = "id") val id: String
                      /*@ColumnInfo(name = "owner") val owner: User*/)

