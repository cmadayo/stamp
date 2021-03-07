package com.example.stamp.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stamp.domain.DomainStamp
import com.example.stamp.modules.Event

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
    /**
     * 画面遷移イベント
     */
    val onTransit = MutableLiveData<Event<Boolean>>()

    /**
     * 新規作成ボタン押下処理
     */
    fun onClickCreateButton() {
        onTransit.value = Event(true)
    }
}