package com.robertvargic.githubusersearch.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "repositoryTable", foreignKeys = [(ForeignKey(entity = User::class, parentColumns = arrayOf("id"), childColumns = arrayOf("userId"), onDelete = ForeignKey.CASCADE))])
data class Repository(@PrimaryKey @ColumnInfo(name = "id") var id: String,
                      @ColumnInfo(name = "userId") var userId: String,
                      @ColumnInfo(name = "name") var name: String,
                      @ColumnInfo(name = "full_name") var fullName: String,
                      @ColumnInfo(name = "html_url") var htmlUrl: String)

