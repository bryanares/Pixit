package com.brian.pixit.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "pixit_photo")
data class photo(
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    @ColumnInfo(name = "url")
    val photoUrl : String,

    @ColumnInfo(name = "author")
    val photoAuthor : String
)