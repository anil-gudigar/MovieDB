package com.app.details.data.remote

import androidx.lifecycle.LiveData
import com.app.common.data.local.DetailsDao
import com.app.common.data.model.Movie
import com.app.core.db.Result
import com.app.core.db.resultLiveData
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 */
@Singleton
class DetailsRepository @Inject constructor(private val dao: DetailsDao,
                                             private val remoteSource:DetailsRemoteDataSource) {

    fun getMovieDetails(id:String): LiveData<Result<Movie>> {
       return resultLiveData(
            databaseQuery = { dao.getMovieDetails(id) },
            networkCall = { remoteSource.fetchData(id) },
            saveCallResult = { it.let { dao.insert(it) } })
    }

}