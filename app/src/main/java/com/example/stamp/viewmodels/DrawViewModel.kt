package com.example.stamp.viewmodels

import android.graphics.Bitmap
import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stamp.domain.DomainStamp
import com.example.stamp.modules.Event
import kotlinx.coroutines.launch

class DrawViewModel @ViewModelInject constructor(
    private val mRepository: IStampRepository
) : ViewModel() {
    /**
     * 保存ボタン押下イベント通知
     */
    val clickedSaveButton = MutableLiveData<Event<Unit>>()

    /**
     * タイトル
     * Memo:2Way-Bindingで、カスタムsetter無しで値を直接代入できるみたい
     */
    var title = MutableLiveData<String>()

    /**
     * 保存ボタン押下処理
     */
    fun onClickSaveButton(@Suppress("UNUSED_PARAMETER") v: View) {
        clickedSaveButton.value = Event(Unit)
    }

    /**
     * スタンプ追加処理
     */
    fun addStamp(bitmap: Bitmap) {
        viewModelScope.launch {
            mRepository.addStamp(DomainStamp(0, title.value ?: "", bitmap))
        }
    }
}