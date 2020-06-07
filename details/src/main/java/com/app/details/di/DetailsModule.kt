package com.app.details.di

import com.app.common.db.AppDatabase
import com.app.core.di.CoreDataModule
import com.app.details.data.remote.DetailsService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [CoreDataModule::class])
class DetailsModule {

    @Singleton
    @Provides
    fun provideDetailsDao(db: AppDatabase) = db.detailsDao()

    @Provides
    fun provideDetailsService(retrofit: Retrofit): DetailsService = retrofit.create(DetailsService::class.java)

}