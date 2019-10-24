package com.app.details.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.common.data.local.DetailsDao
import com.app.common.db.AppDatabase
import com.app.details.data.remote.DetailsRemoteDataSource
import com.app.details.data.remote.DetailsRepository
import com.app.details.data.remote.DetailsService
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito

@RunWith(JUnit4::class)
class DetailsRepositoryTest {
    private lateinit var repository: DetailsRepository
    private val dao = Mockito.mock(DetailsDao::class.java)
    private val service = Mockito.mock(DetailsService::class.java)
    private val remoteDataSource = DetailsRemoteDataSource(service)
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        val db = Mockito.mock(AppDatabase::class.java)
        Mockito.`when`(db.detailsDao()).thenReturn(dao)
        Mockito.`when`(db.runInTransaction(ArgumentMatchers.any())).thenCallRealMethod()
        repository = DetailsRepository(dao, remoteDataSource)
    }

    @Test
    fun loadLegoSetsFromNetwork() {
        runBlocking {
            Mockito.verify(dao, Mockito.never()).getMovieDetails("475557")
            Mockito.verifyZeroInteractions(dao)
        }
    }

}