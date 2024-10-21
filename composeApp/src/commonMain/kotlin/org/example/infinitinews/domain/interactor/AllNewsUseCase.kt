package org.example.infinitinews.domain.interactor

import org.example.infinitinews.data.repository.NewsRepository

class AllNewsUseCase constructor(private val repository: NewsRepository) {

    suspend operator fun invoke() =
        repository.getAllNews()
    }