package com.example.stamp.viewmodels

import android.app.Application
import android.graphics.Bitmap
import android.util.Log
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.stamp.database.getDatabase
import com.example.stamp.domain.DomainStamp
import com.example.stamp.repository.StampsRepository
import kotlinx.coroutines.launch

class DrawViewModel @ViewModelInject constructor(
    application: Application,
    private val repository: IStampRepository
) : AndroidViewModel(application) {
//    private val stampsRepository = StampsRepository(getDatabase(application))
    val clickedSavedButton = MutableLiveData<Unit>()

    fun onClickSaveButton(@Suppress("UNUSED_PARAMETER") v: View) {
        Log.e("test", "test")
        clickedSavedButton.postValue(Unit)
    }

    fun addStamp(bitmap: Bitmap) {
        viewModelScope.launch {
            repository.addStamp(DomainStamp(0, "test", bitmap))
        }
    }

//    class Factory(val app: Application) : ViewModelProvider.Factory {
//        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//            if (modelClass.isAssignableFrom(DrawViewModel::class.java)) {
//                @Suppress("UNCHECKED_CAST")
//                return DrawViewModel(app) as T
//            }
//            throw IllegalArgumentException("Unable to construct viewmodel")
//        }
//    }
}