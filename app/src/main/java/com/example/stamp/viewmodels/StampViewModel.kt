package com.example.stamp.viewmodels

import android.app.Application
import android.graphics.Bitmap
import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.example.stamp.database.getDatabase
import com.example.stamp.domain.DomainStamp
import com.example.stamp.repository.StampsRepository
import kotlinx.coroutines.launch

class StampViewModel(application: Application) : AndroidViewModel(application) {

    private val stampsRepository = StampsRepository(getDatabase(application))

    /**
     * Stamps displayed on the screen.
     */
    val stampList = stampsRepository.stamps

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(StampViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return StampViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}