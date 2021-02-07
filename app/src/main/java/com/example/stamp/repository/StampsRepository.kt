package com.example.stamp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.stamp.database.StampEntity
import com.example.stamp.database.StampsDatabase
import com.example.stamp.database.asDomainModel
import com.example.stamp.domain.DomainStamp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StampsRepository(private val database: StampsDatabase) {
    val stamps: LiveData<List<DomainStamp>> = Transformations.map(database.stampDao.getStamps()) {
        it.asDomainModel()
    }

    suspend fun addStamp(stamp: DomainStamp) {
        withContext(Dispatchers.IO) {
            database.stampDao.insert(stamp.asDatabaseEntity())
        }
    }
}