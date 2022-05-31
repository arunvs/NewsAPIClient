package com.fairfaxmedia.newsapiclient.data.api


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

class NewsAPIServiceTest {

    private lateinit var service: NewsAPIService
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPIService::class.java)
    }

    private fun enqueueMockResponse(
      fileName:String
    ){
      val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
      val source = inputStream.source().buffer()
      val mockResponse = MockResponse()
      mockResponse.setBody(source.readString(Charsets.UTF_8))
      server.enqueue(mockResponse)

    }

    @Test
    fun getTopHeadlines_sentRequest_receivedExpected(){
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val responseBody = service.getTopHeadlines().body()
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/1/coding_test/13ZZQX/full")
        }
    }

    @Test
    fun getTopHeadlines_receivedResponse_correctDisplayName(){
      runBlocking {
          enqueueMockResponse("newsresponse.json")
          val responseBody = service.getTopHeadlines().body()
          val articlesList = responseBody!!.displayName
          assertThat(articlesList).isEqualTo("AFR iPad Editor's Choice")
      }
    }

    @Test
    fun getTopHeadlines_receivedResponse_correctContent(){
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val responseBody = service.getTopHeadlines().body()
            val articlesList = responseBody!!.assets
            val article = articlesList[0]
            assertThat(article.id).isEqualTo(1520551987)
            assertThat(article.byLine).isEqualTo("Joe Aston")
            assertThat(article.legalStatus).isEqualTo("Approved")
        }
    }

    @After
    fun tearDown() {
       server.shutdown()
    }
}