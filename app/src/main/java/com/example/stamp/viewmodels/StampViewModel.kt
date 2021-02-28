package com.example.stamp.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.stamp.domain.DomainStamp

interface IStampRepository {
    suspend fun addStamp(stamp: DomainStamp)
    fun getStamps(): LiveData<List<DomainStamp>>
    fun modifyStamp()
}

class StampViewModel
    @ViewModelInject
    constructor(private val repository: IStampRepository)
    : ViewModel() {

    /**
     * Stamps displayed on the screen.
     */
    val stampList = repository.getStamps()
}