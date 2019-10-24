package com.app.discover.data.remote

import com.app.discover.data.model.DiscoverMovie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface DiscoverService {

    @GET("discover/movie")
    suspend fun getMoviesList(
        @Query("api_key") api_key: String? = null,
        @Query("language") language: String? = null,
        @Query("sort_by") sort_by: String? = null,
        @Query("include_adult") include_adult: String? = null,
        @Query("include_video") include_video: String? = null,
        @Query("page") page: Int? = null
    ): Response<DiscoverMovie>

}