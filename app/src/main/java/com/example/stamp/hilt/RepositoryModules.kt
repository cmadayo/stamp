package com.example.stamp.hilt

import com.example.stamp.repository.StampsRepository
import com.example.stamp.viewmodels.IStampRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * Hilt用のRepositoryモジュール
 */
@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {
    /**
     * StampRepositoryのモジュール宣言
     */
    @Binds
    @Singleton
    abstract fun bindStampRepository(impl: StampsRepository): IStampRepository
}