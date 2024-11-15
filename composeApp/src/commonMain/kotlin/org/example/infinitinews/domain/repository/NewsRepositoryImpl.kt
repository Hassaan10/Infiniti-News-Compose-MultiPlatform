package org.example.infinitinews.domain.repository

import kotlinx.coroutines.flow.Flow
import org.example.infinitinews.data.network.ApiResponse
import org.example.infinitinews.data.network.NetworkService
import kotlinx.coroutines.flow.flow
import org.example.infinitinews.data.LOCALE_US
import org.example.infinitinews.data.repository.NewsRepository

class NewsRepositoryImpl constructor(private val apiService: NetworkService):
    NewsRepository {
    override suspend fun getHeadLines(): Flow<ApiResponse> {
        return flow {
            emit(ApiResponse.Loading)
            try {
                val result = apiService.getHeadLinesNews(LOCALE_US)
                emit(ApiResponse.Success(result.articles))
            }
            catch (e: Exception) {
                emit(ApiResponse.Error(e))
            }
        }
    }

    override suspend fun getAllNews(): Flow<ApiResponse> {
        return flow {
            emit(ApiResponse.Loading)
            try {
                val result = apiService.getAllNews()
                emit(ApiResponse.Success(result.articles))
            }
            catch (e: Exception) {
                emit(ApiResponse.Error(e))
            }
        }
    }
}