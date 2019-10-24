package com.app.common.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.common.data.model.Movie

/**
 * The Data Access Object for the Movie Details class.
 */
@Dao
interface DetailsDao {

    @Query("SELECT * FROM movie WHERE id = :id")
    fun getMovieDetails(id: String): LiveData<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(plants: Movie)
}