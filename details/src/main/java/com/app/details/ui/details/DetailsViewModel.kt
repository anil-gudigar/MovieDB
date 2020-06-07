package com.app.details.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.details.data.remote.DetailsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @Inject constructor(repository: DetailsRepository) : ViewModel() {

    lateinit var id: String

    val movie by lazy { repository.getMovieDetails(id) }

}