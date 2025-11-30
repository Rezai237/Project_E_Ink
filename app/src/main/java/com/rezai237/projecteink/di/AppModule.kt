package com.rezai237.projecteink.di

import com.rezai237.projecteink.data.manager.SystemSettingsManagerImpl
import com.rezai237.projecteink.domain.manager.SystemSettingsManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindSystemSettingsManager(
        systemSettingsManagerImpl: SystemSettingsManagerImpl
    ): SystemSettingsManager
}
