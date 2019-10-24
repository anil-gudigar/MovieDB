package com.app.moviedb.di

import androidx.lifecycle.ViewModelProvider
import com.app.core.di.ViewModelFactory
import com.app.details.di.DetailsViewModelModule
import com.app.discover.di.DiscoverViewModelModule

import dagger.Binds
import dagger.Module

@Suppress("unused")
@Module(includes = [ DiscoverViewModelModule::class,DetailsViewModelModule::class])
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
