package org.example.infinitinews.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.example.infinitinews.data.ALL_NEWS_URL
import org.example.infinitinews.data.COUNTRY
import org.example.infinitinews.data.HEADLINES_URL
import org.example.infinitinews.data.model.NewsResponse

class NetworkService(private val client: HttpClient) {

    suspend fun getAllNews():NewsResponse {
        return client.get(ALL_NEWS_URL).body() as NewsResponse
    }

    suspend fun getHeadLinesNews(locale:String): NewsResponse {
        return client.get(HEADLINES_URL) {
            url {
                parameters.append(COUNTRY, locale)
            }
        }.body() as NewsResponse
    }

}