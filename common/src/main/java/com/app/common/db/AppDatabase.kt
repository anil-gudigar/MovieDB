package com.app.common.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.common.data.converter.Converters
import com.app.common.data.local.DetailsDao
import com.app.common.data.local.DiscoverDao
import com.app.common.data.model.Genres
import com.app.common.data.model.Movie

/**
 * The Room database for this app
 */
@Database(entities = [Movie::class,Genres::class],
    version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun discoverDao(): DiscoverDao

    abstract fun detailsDao(): DetailsDao

    companion object {
        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance
                    ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "movie-db")
                .addCallback(object : RoomDatabase.Callback() {
                })
                .build()
        }
    }
}
