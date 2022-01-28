package com.melatech.wipronews.data.api

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

/**
 *created by Jason Junior Calvert on 27/01/2022.
 */
//Unit Test of the NewsAPIService class
class NewsAPIServiceTest {

    private lateinit var service: NewsAPIService
    //this MockWebServer makes fake api calls for testing purposes
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        //initialise mock components
        server = MockWebServer()
        //construct the service with Retrofit Builder
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPIService::class.java)
    }

    private fun enqueueMockResponse(
        //json file name
        fileName: String
    ){
        //get the json file with inputStream
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)

    }

    //subject under test_action_result state
    @Test
    fun getTopHeadlines_sendRequest_receivedExpected(){
        //this coroutine builder is for testing
        //blocks current thread until completed
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            //call to the server
            val responseBody = service.getTopHeadlines_service("us", 1)
            //request received by the mock server
            val request = server.takeRequest()
            assertThat(responseBody).isNotNull()
            assertThat(request.path).isEqualTo("/v2/top-headlines?country=us&page=1&apiKey=b80371869b824d829bc131cf682d63cc")
        }
    }

    //subject under test_action_result state
    @Test
    fun getTopHeadlines_receivedResponse_correctPageSize(){
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val responseBody = service.getTopHeadlines_service("us", 1).body()
            val articleList = responseBody!!.articles
            assertThat(articleList.size).isEqualTo(20)
        }
    }

    //subject under test_action_result state
    @Test
    fun getTopHeadlines_receivedResponse_correctContent(){
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val responseBody = service.getTopHeadlines_service("us", 1).body()
            val articleList = responseBody!!.articles
            val article = articleList[0]
            assertThat(article.author).isEqualTo("Andrew E. Kramer")
            assertThat(article.url).isEqualTo("https://www.nytimes.com/live/2022/01/27/world/ukraine-russia-us")
            assertThat(article.publishedAt).isEqualTo("2022-01-27T08:21:00Z")
        }
    }



    @After
    fun tearDown() {
        //stops the server when testing operations are completed
        server.shutdown()
    }
}