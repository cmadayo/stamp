package com.example.stamp.businesslogic.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stamp.businesslogic.dataaccess.IStampRepository
import com.example.stamp.modules.Event

/**
 * ViewModel:スタンプ一覧画面
 */
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