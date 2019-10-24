package com.app.discover.di


import com.app.discover.ui.discover.DiscoverFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class DiscoverFragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeDiscoverFragment(): DiscoverFragment
}
