package com.app.details.ui.details

import androidx.lifecycle.ViewModel
import com.app.details.data.remote.DetailsRepository
import javax.inject.Inject

class DetailsViewModel @Inject constructor(repository: DetailsRepository) : ViewModel() {

    lateinit var id: String

    val movie by lazy { repository.getMovieDetails(id) }

}