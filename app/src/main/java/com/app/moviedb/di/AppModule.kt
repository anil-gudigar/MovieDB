package com.app.moviedb.di

import android.app.Application
import com.app.common.db.AppDatabase
import com.app.core.di.CoreDataModule
import com.app.details.di.DetailsModule
import com.app.discover.di.DiscoverModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [ViewModelModule::class,
        DiscoverModule::class,
        DetailsModule::class,
        CoreDataModule::class]
)
class AppModule {

    @Singleton
    @Provides
    fun provideDb(app: Application) = AppDatabase.getInstance(app)

}
