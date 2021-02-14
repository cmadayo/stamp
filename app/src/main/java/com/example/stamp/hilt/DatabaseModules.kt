package com.example.stamp.hilt

import android.content.Context
import androidx.room.Room
import com.example.stamp.database.StampDao
import com.example.stamp.database.StampsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


/**
 * Hilt用のデータベースモジュール
 */
@Module
@InstallIn(ApplicationComponent::class)
class DataModule {
    /**
     * Hilt用のデータベースモジュール宣言
     * Memo:@ApplicationContextにてコンテキストを取得できる
     */
    @Provides
    @Singleton
    fun getDatabase(@ApplicationContext context: Context): StampsDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            StampsDatabase::class.java,
            "stamps"
        ).build()
    }

    /**
     * Hilt用のDaoモジュール宣言
     * Memo:getDatabaseで宣言したモジュール(StampsDatabase)をパラメータでもらうことが可能
     */
    @Provides
    @Singleton
    fun getDao(db: StampsDatabase): StampDao {
        return db.stampDao
    }
}