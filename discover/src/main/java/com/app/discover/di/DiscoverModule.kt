package com.app.discover.di

import com.app.common.data.remote.BaseService
import com.app.common.db.AppDatabase
import com.app.common.di.DiscoverAPI
import com.app.discover.data.remote.DiscoverService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [])
class DiscoverModule {

    @Singleton
    @Provides
    fun provideDiscoverDao(db: AppDatabase) = db.discoverDao()

    @Singleton
    @Provides
    fun provideDiscoverService(
        @DiscoverAPI okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ) = provideService(okhttpClient, converterFactory, DiscoverService::class.java)

    @DiscoverAPI
    @Provides
    fun providePrivateOkHttpClient(
        upstreamClient: OkHttpClient
    ): OkHttpClient {
        return upstreamClient.newBuilder().build()
    }

    private fun createRetrofit(
        okhttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BaseService.ENDPOINT)
            .client(okhttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    private fun <T> provideService(okhttpClient: OkHttpClient,
                                   converterFactory: GsonConverterFactory, clazz: Class<T>): T {
        return createRetrofit(okhttpClient, converterFactory).create(clazz)
    }

}