package com.example.stamp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.stamp.database.*
import com.example.stamp.domain.DomainStamp
import com.example.stamp.viewmodels.IStampRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

class StampsRepository @Inject constructor() : IStampRepository {
//    val stamps: LiveData<List<DomainStamp>> = Transformations.map(database.stampDao.getStamps()) {
//        it.asDomainModel()
//    }
    @Inject lateinit var dao: StampDao


    override fun getStamps(): LiveData<List<DomainStamp>> {
        return Transformations.map(dao.getStamps()) {
            it.asDomainModel()
        }
    }

    override suspend fun addStamp(stamp: DomainStamp) {
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