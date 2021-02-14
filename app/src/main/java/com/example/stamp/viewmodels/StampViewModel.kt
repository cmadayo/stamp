package com.example.stamp.viewmodels

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.stamp.domain.DomainStamp

interface IStampRepository {
    suspend fun addStamp(stamp: DomainStamp)
    fun getStamps(): LiveData<List<DomainStamp>>
    fun modifyStamp()
}

class StampViewModel
    @ViewModelInject
    constructor(application: Application, private val repository: IStampRepository)
    : AndroidViewModel(application) {

    /**
     * Stamps displayed on the screen.
     */
    val stampList = repository.getStamps()
}