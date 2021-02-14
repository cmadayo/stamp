package com.example.stamp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.stamp.database.StampDao
import com.example.stamp.database.asDomainModel
import com.example.stamp.domain.DomainStamp
import com.example.stamp.viewmodels.IStampRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * スタンプリポジトリ
 */
class StampsRepository @Inject constructor() : IStampRepository {
    /**
     * Dao
     * Memo:HiltでDI
     */
    @Inject lateinit var dao: StampDao


    /**
     * スタンプ取得
     */
    override fun getStamps(): LiveData<List<DomainStamp>> {
        // Entity(DBのDTO)からDomain(ViewのDTO)へ変換
        return Transformations.map(dao.getStamps()) {
            it.asDomainModel()
        }
    }

    /**
     * スタンプ追加
     */
    override suspend fun addStamp(stamp: DomainStamp) {
        // withContextで
        withContext(Dispatchers.IO) {
            dao.insert(stamp.asDatabaseEntity())
        }
    }

    override fun modifyStamp() {
        Transformations.map(dao.getStamps()) {
            it.asDomainModel()
        }
    }

}