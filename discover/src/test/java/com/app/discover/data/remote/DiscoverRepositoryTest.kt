package com.app.discover.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.common.data.local.DiscoverDao
import com.app.common.db.AppDatabase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class DiscoverRepositoryTest {
    private lateinit var repository: DiscoverRepository
    private val dao = mock(DiscoverDao::class.java)
    private val service = mock(DiscoverService::class.java)
    private val remoteDataSource = DiscoverRemoteDataSource(service)
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        val db = mock(AppDatabase::class.java)
        `when`(db.discoverDao()).thenReturn(dao)
        `when`(db.runInTransaction(ArgumentMatchers.any())).thenCallRealMethod()
        repository = DiscoverRepository(dao, remoteDataSource)
    }

    @Test
    fun loadLegoSetsFromNetwork() {
        runBlocking {
            verify(dao, never()).getDiscoverMovie()
            verifyZeroInteractions(dao)
        }
    }

}