package com.app.common.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.app.common.data.converter.Converters
import com.app.common.data.remote.BaseService
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie")
@TypeConverters(Converters::class)
data class Movie(
    @field:SerializedName("original_language")
    val original_language: String? = null,
    @field:SerializedName("imdb_id")
    val imdb_id: String? = null,
    @field:SerializedName("video")
    val video: String? = null,
    @field:SerializedName("title")
    val title: String? = null,
    @field:SerializedName("backdrop_path")
    val backdrop_path: String? = null,
    @field:SerializedName("revenue")
    val revenue: String? = null,
    @field:SerializedName("genres")
    val genres: List<Genres>? = null,
    @field:SerializedName("popularity")
    val popularity: String? = null,
    @field:SerializedName("id")
    @PrimaryKey
    val id: String,
    @field:SerializedName("vote_count")
    val vote_count: String? = null,
    @field:SerializedName("budget")
    val budget: String? = null,
    @field:SerializedName("overview")
    val overview: String? = null,
    @field:SerializedName("original_title")
    val original_title: String? = null,
    @field:SerializedName("runtime")
    val runtime: String? = null,
    @field:SerializedName("poster_path")
    val poster_path: String? = null,
    @field:SerializedName("release_date")
    val release_date: String? = null,
    @field:SerializedName("vote_average")
    val vote_average: String? = null,
    @field:SerializedName("tagline")
    val tagline: String? = null,
    @field:SerializedName("adult")
    val adult: String? = null,
    @field:SerializedName("homepage")
    val homepage: String? = null,
    @field:SerializedName("status")
    val status: String? = null
) {

    fun getVoteAverage(): String? {
        return vote_average?.let { (it.toFloat() * 10).toInt().toString().plus("%") }
    }

    fun getVoteAverageValue(): Int? {
        return vote_average?.let { (it.toFloat() * 10).toInt() }
    }

    fun getBackDropImageUrl(): String {
        return BaseService.IMAGE_ENDPOINT + backdrop_path
    }

    fun getCompleteImageUrl(): String {
        return BaseService.IMAGE_ENDPOINT + poster_path
    }

    override fun toString(): String {
        return "[original_language = $original_language, imdb_id = $imdb_id, video = $video, title = $title, backdrop_path = $backdrop_path, revenue = $revenue, genres = $genres, popularity = $popularity, id = $id, vote_count = $vote_count, budget = $budget, overview = $overview, original_title = $original_title, runtime = $runtime, poster_path = $poster_path, release_date = $release_date, vote_average = $vote_average, tagline = $tagline, adult = $adult, homepage = $homepage, status = $status]"
    }
}


