package com.app.common.data.remote

interface BaseService {

    companion object {
        const val ENDPOINT = "https://api.themoviedb.org/3/"
        const val IMAGE_ENDPOINT = "https://image.tmdb.org/t/p/w1280"
    }
}