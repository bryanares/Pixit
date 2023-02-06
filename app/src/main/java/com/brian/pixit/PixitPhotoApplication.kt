package com.brian.pixit

import android.app.Application
import com.brian.pixit.data.PhotoDatabase

class PixitPhotoApplication : Application() {
    val database : PhotoDatabase by lazy { PhotoDatabase.getDatabase(this) }
}