package com.example.stamp.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StampDao {
    @Query("select * from stampentity")
    fun getStamps(): LiveData<List<StampEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert( stamps: StampEntity)
}

@Database(entities = [StampEntity::class], version = 1, exportSchema = false)
@TypeConverters(BitmapConverter::class)
abstract class StampsDatabase: RoomDatabase() {
    abstract val stampDao: StampDao
}

private lateinit var INSTANCE: StampsDatabase

fun getDatabase(context: Context): StampsDatabase {
    synchronized(StampsDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                StampsDatabase::class.java,
                "stamps"
            ).build()
        }
    }
    return INSTANCE
}