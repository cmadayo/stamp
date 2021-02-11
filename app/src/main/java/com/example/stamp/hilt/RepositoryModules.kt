package com.example.stamp.hilt

import com.example.stamp.repository.StampsRepository
import com.example.stamp.viewmodels.IStampRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindStampRepository(impl: StampsRepository): IStampRepository
}