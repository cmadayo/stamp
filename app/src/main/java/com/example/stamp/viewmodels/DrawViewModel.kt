package com.example.stamp.viewmodels

import android.app.Application
import android.graphics.Bitmap
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.stamp.domain.DomainStamp
import kotlinx.coroutines.launch

class DrawViewModel @ViewModelInject constructor(
    application: Application,
    private val repository: IStampRepository
) : AndroidViewModel(application) {
    val clickedSavedButton = MutableLiveData<Unit>()

    fun onClickSaveButton(@Suppress("UNUSED_PARAMETER") v: View) {
        clickedSavedButton.postValue(Unit)
    }

    fun addStamp(bitmap: Bitmap) {
        viewModelScope.launch {
            repository.addStamp(DomainStamp(0, "test", bitmap))
        }
    }
}