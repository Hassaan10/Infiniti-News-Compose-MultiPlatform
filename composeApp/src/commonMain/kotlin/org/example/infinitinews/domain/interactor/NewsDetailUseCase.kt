package org.example.infinitinews.domain.interactor

import org.example.infinitinews.data.repository.NewsRepository

class NewsDetailUseCase constructor(private val repository: NewsRepository) {

    suspend operator fun invoke() =
        repository.getAllNews()
    }