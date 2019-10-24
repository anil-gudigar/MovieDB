package com.app.discover.data.remote

import com.app.common.data.local.DiscoverDao
import com.app.core.db.resultLiveData
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 */
@Singleton
class DiscoverRepository @Inject constructor(private val dao: DiscoverDao,
                                             private val remoteSource:DiscoverRemoteDataSource) {

    val movies = resultLiveData(
        databaseQuery = { dao.getDiscoverMovie() },
        networkCall = { remoteSource.fetchData() },
        saveCallResult = { it.results?.let { it1 -> dao.insertAll(it1) } })

}