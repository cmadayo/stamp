package com.example.stamp.businesslogic.dataaccess

import androidx.lifecycle.LiveData
import com.example.stamp.businesslogic.domains.DomainStamp

/**
 * スタンプリポジトリ
 */
interface IStampRepository {
    /**
     * スタンプ追加
     */
    suspend fun addStamp(stamp: DomainStamp)

    /**
     * スタンプ取得
     */
    fun getStamps(): LiveData<List<DomainStamp>>

    /**
     * スタンプ修正
     */
    fun modifyStamp()
}
