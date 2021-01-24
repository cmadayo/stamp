package com.example.stamp.viewmodels

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DrawViewModel(application: Application) : AndroidViewModel(application) {
    fun onClickSaveButton(v: View) {
        Log.e("test", "test")
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DrawViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DrawViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}