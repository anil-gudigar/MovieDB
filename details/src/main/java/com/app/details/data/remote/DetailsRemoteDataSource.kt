package com.app.details.data.remote

import com.app.common.BuildConfig
import com.app.core.api.BaseDataSource
import javax.inject.Inject

/**
 * Works with the Discover to get data.
 */
class DetailsRemoteDataSource @Inject constructor(private val service: DetailsService) : BaseDataSource() {

    suspend fun fetchData(id:String) = getResult { service.getMovie(id,BuildConfig.API_DEVELOPER_TOKEN, "en") }

}