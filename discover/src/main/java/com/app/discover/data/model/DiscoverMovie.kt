package com.app.discover.data.model

import com.app.common.data.model.Movie
import com.google.gson.annotations.SerializedName

class DiscoverMovie(
    @field:SerializedName("page")
    var page: String? = null,
    @field:SerializedName("total_pages")
    var total_pages: String? = null,
    @field:SerializedName("results")
    var results: ArrayList<Movie>? = null,
    @field:SerializedName("total_results")
    var total_results: String? = null
) {
    override fun toString(): String {
        return " [page = $page, total_pages = $total_pages, results = $results, total_results = $total_results]"
    }
}
