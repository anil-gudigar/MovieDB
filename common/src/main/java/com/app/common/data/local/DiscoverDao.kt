package com.app.common.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.common.data.model.Movie

/**
 * The Data Access Object for the Discover Movie class.
 */
@Dao
interface DiscoverDao {

    @Query("SELECT * FROM movie")
    fun getDiscoverMovie(): LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movieList: List<Movie>)
}
