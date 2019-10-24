package com.app.discover.data.remote

import com.app.common.BuildConfig
import com.app.core.api.BaseDataSource
import javax.inject.Inject

/**
 * Works with the Discover to get data.
 */
class DiscoverRemoteDataSource @Inject constructor(private val service: DiscoverService) : BaseDataSource() {

    suspend fun fetchData() = getResult { service.getMoviesList(BuildConfig.API_DEVELOPER_TOKEN, "en", "popularity.desc","false","false",1) }

}