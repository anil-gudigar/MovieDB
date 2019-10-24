package com.app.details.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.details.data.remote.DetailsService
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
class DetailsServiceTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: DetailsService

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DetailsService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun requestDetailsMovie() {
        runBlocking {
            enqueueResponse("movie.json")
            val resultResponse = service.getMovie("475557").body()

            val request = mockWebServer.takeRequest()
            Assert.assertNotNull(resultResponse)
            Assert.assertThat(request.path, CoreMatchers.`is`("/movie/475557"))
        }
    }

    @Test
    fun getDetailsMovieResponse() {
        runBlocking {
            enqueueResponse("movie.json")
            val resultResponse = service.getMovie("475557").body()
            val movieList = resultResponse

            Assert.assertThat(resultResponse?.id?.toInt(), CoreMatchers.`is`(475557))
            Assert.assertThat(movieList?.genres?.size, CoreMatchers.`is`(3))
        }
    }

    @Test
    fun getMoiveItem() {
        runBlocking {
            enqueueResponse("movie.json")
            val resultResponse = service.getMovie("475557").body()
            val movie= resultResponse

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
                    CoreMatchers.`is`("2019-10-02")
                )
            }
        }
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader?.getResourceAsStream("$fileName")
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