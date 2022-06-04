package com.assignment.weatherapp.data.api

import com.assignment.weatherapp.WeatherAPIService
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherAPIServiceTest {

    private lateinit var service: WeatherAPIService
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherAPIService::class.java)
    }

    private fun enqueueMockResponse(
        fileName: String
    ) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)

    }


    @Test
    fun getSearchedLocation_receivedResponse_correctContent() {
        runBlocking {
            enqueueMockResponse("weatherresponse.json")
            val responseBody = service.getSearchedLocation("London").body()
            val location = responseBody!!.location
            val current = responseBody!!.current

            assertThat(location?.name).isEqualTo("London")
            assertThat(location?.region).isEqualTo("City of London, Greater London")

        }
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}