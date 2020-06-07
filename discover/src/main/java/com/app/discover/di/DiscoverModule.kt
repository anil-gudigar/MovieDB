package com.app.discover.di

import com.app.common.db.AppDatabase
import com.app.common.di.DiscoverAPI
import com.app.core.di.CoreDataModule
import com.app.discover.data.remote.DiscoverService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [CoreDataModule::class])
class DiscoverModule {

    @Singleton
    @Provides
    fun provideDiscoverDao(db: AppDatabase) = db.discoverDao()

    @Provides
    fun provideDiscoverService(retrofit: Retrofit): DiscoverService = retrofit.create(DiscoverService::class.java)

}