package com.app.discover.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import org.hamcrest.CoreMatchers
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class DiscoverServiceTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: DiscoverService

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DiscoverService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun requestDiscoverMovie() {
        runBlocking {
            enqueueResponse("discover.json")
            val resultResponse = service.getMoviesList().body()

            val request = mockWebServer.takeRequest()
            Assert.assertNotNull(resultResponse)
            Assert.assertThat(request.path, CoreMatchers.`is`("/discover/movie"))
        }
    }

    @Test
    fun getDiscoverMovieResponse() {
        runBlocking {
            enqueueResponse("discover.json")
            val resultResponse = service.getMoviesList().body()
            val movieList = resultResponse!!.results

            Assert.assertThat(resultResponse.page?.toInt(), CoreMatchers.`is`(1))
            Assert.assertThat(movieList?.size, CoreMatchers.`is`(20))
        }
    }

    @Test
    fun getMoiveItem() {
        runBlocking {
            enqueueResponse("discover.json")
            val resultResponse = service.getMoviesList().body()
            val movieList = resultResponse!!.results

            val movie = movieList?.get(0)
            movie?.let {
                Assert.assertThat(it.id, CoreMatchers.`is`("475557"))
                Assert.assertThat(it.title, CoreMatchers.`is`("Joker"))
                Assert.assertThat(it.vote_average, CoreMatchers.`is`( "8.6"))
                Assert.assertThat(it.overview, CoreMatchers.`is`("During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure."))
                Assert.assertThat(
                    it.backdrop_path,
                    CoreMatchers.`is`("/n6bUvigpRFqSwmPp1m2YADdbRBc.jpg")
                )
                Assert.assertThat(
                    it.poster_path,
                    CoreMatchers.`is`("/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg")
                )
                Assert.assertThat(
                    it.release_date,
                    CoreMatchers.`is`("2019-10-04")
                )
            }
        }
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader?.getResourceAsStream("test-data/$fileName")
        val source = Okio.buffer(Okio.source(inputStream))
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(mockResponse.setBody(
            source.readString(Charsets.UTF_8))
        )
    }
}