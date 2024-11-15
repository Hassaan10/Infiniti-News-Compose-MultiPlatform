package org.example.infinitinews.data.di

import org.example.infinitinews.data.repository.NewsRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.infinitinews.data.API_KEY
import org.example.infinitinews.data.API_KEY_HEADER
import org.example.infinitinews.data.BASE_URL
import org.example.infinitinews.data.network.NetworkService
import org.example.infinitinews.domain.repository.NewsRepositoryImpl
import org.example.infinitinews.domain.interactor.AllNewsUseCase
import org.example.infinitinews.domain.interactor.HeadlinesUseCase
import org.example.infinitinews.ui.viewmodels.MainViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val provideNetworkModule = module {
    fun provideNetworkClient(
    ): HttpClient {
        return HttpClient {
            defaultRequest {
                url(BASE_URL)
                headers.append(API_KEY_HEADER, API_KEY)
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.HEADERS
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
        }
    }

    singleOf(::provideNetworkClient)
    single<NetworkService> { NetworkService(get()) }

}

val provideNewsModule = module {
    single<NewsRepository> { NewsRepositoryImpl(get()) }
}

val provideAllNewsUseCaseModule = module {
    single {
        AllNewsUseCase(get())
    }
}

val provideHeadlinesUseCaseModule = module {
    single {
        HeadlinesUseCase(get())
    }
}

val provideViewModelModule = module {
    single {
        MainViewModel()
    }
}

val appModules = listOf(provideNetworkModule, provideNewsModule, provideAllNewsUseCaseModule, provideHeadlinesUseCaseModule, provideViewModelModule)
