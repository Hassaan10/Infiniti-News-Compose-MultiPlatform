package org.example.infinitinews

import org.koin.core.context.startKoin
import org.example.infinitinews.data.di.appModules

    fun initKoin(){
        startKoin {
            modules(appModules)
        }
    }
