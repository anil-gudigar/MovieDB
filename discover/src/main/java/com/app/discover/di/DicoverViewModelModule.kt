package com.app.discover.di

import androidx.lifecycle.ViewModel
import com.app.core.di.ViewModelKey
import com.app.discover.ui.discover.DiscoverViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class DiscoverViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(DiscoverViewModel::class)
    abstract fun bindDiscoverViewModel(viewModel: DiscoverViewModel): ViewModel
}
