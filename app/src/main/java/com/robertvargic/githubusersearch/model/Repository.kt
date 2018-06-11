package com.robertvargic.githubusersearch.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

//?@Entity(tableName = "repository") //foreignKeys = @ForeignKey(entity = User.class)
@Entity(tableName = "repositoryTable", foreignKeys = [(ForeignKey(entity = User::class, parentColumns = arrayOf("id"), childColumns = arrayOf("userId"), onDelete = ForeignKey.CASCADE))])

data class Repository(@PrimaryKey @Json("id") @ColumnInfo(name = "id") val id: String,
//                      @Embedded @ColumnInfo(name = "userField") val user: User,
                      @ColumnInfo(name = "userId") val userId: String,
                      @ColumnInfo(name = "name") val name: String,
                      @Json("full_name") @ColumnInfo(name = "full_name") val fullName: String,
                      @Json("html_url") @ColumnInfo(name ="html_url") val htmlUrl: String)

