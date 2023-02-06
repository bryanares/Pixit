package com.brian.pixit.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface PhotoDao {

    @Insert(onConflict =  OnConflictStrategy.IGNORE)
    suspend fun insert (photo: Photo)

    @Delete
    suspend fun delete (photo: Photo)

    @Query("SELECT * FROM pixit_photo ORDER BY id ASC")
    fun getPhotos() : Flow<List<Photo>>


}