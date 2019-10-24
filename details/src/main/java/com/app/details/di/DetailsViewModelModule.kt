package com.app.details.di

import androidx.lifecycle.ViewModel
import com.app.core.di.ViewModelKey
import com.app.details.ui.details.DetailsViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class DetailsViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun bindDiscoverViewModel(viewModel: DetailsViewModel): ViewModel
}