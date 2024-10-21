package org.example.infinitinews.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.example.infinitinews.data.model.NewsResponse

class NetworkService(private val client: HttpClient) {

    suspend fun getAllNews():NewsResponse {
        return client.get("everything?q=keyword").body() as NewsResponse
    }

    suspend fun getHeadLinesNews(locale:String): NewsResponse {
        return client.get("top-headlines") {
            url {
                parameters.append("country", locale)
            }
        }.body() as NewsResponse
    }

}