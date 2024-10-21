package org.example.infinitinews.data.repository

import org.example.infinitinews.data.network.ApiResponse
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getHeadLines(): Flow<ApiResponse>
    suspend fun getAllNews(): Flow<ApiResponse>

}