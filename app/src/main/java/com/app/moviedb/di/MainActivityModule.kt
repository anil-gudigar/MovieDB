package com.app.moviedb.di


import com.app.details.di.DetailsFragmentBuildersModule
import com.app.discover.di.DiscoverFragmentBuildersModule
import com.app.moviedb.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [DiscoverFragmentBuildersModule::class,DetailsFragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}
