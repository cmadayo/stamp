package com.example.stamp.hilt

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.stamp.database.StampDao
import com.example.stamp.database.StampsDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun getDatabase(@ApplicationContext context: Context): StampsDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            StampsDatabase::class.java,
            "stamps"
        ).build()
    }

    @Provides
    @Singleton
    fun getDao(db: StampsDatabase): StampDao {
        return db.stampDao
    }
}