package com.brian.pixit.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Photo::class], version = 1, exportSchema = false)
abstract class PhotoDatabase : RoomDatabase() {
    abstract fun photoDao() : PhotoDao

    companion object {
        @Volatile
        private var INSTANCE: PhotoDatabase? = null
        fun getDatabase(context: Context): PhotoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PhotoDatabase::class.java,
                    "pixit_photo_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}