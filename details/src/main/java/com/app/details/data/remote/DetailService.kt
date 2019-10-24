package com.app.details.data.remote

import com.app.common.data.model.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailsService {

    @GET("movie/{movieid}")
    suspend fun getMovie(
        @Path("movieid") movieid:String?= null,
        @Query("api_key") api_key: String? = null,
        @Query("language") language: String? = null
    ): Response<Movie>

}