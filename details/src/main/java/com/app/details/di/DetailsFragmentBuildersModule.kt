package com.app.details.di

import com.app.details.ui.details.DetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class DetailsFragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeDetailsFragment(): DetailsFragment
}