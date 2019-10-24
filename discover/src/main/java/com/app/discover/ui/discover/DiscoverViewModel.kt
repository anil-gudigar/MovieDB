package com.app.discover.ui.discover

import androidx.lifecycle.ViewModel
import com.app.discover.data.remote.DiscoverRepository
import javax.inject.Inject

class DiscoverViewModel @Inject constructor(repository: DiscoverRepository) : ViewModel() {

    val moviesList= repository.movies
}