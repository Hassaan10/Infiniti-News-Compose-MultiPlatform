package org.example.infinitinews

import androidx.compose.runtime.*
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.crossfade
import coil3.util.DebugLogger
import org.example.infinitinews.ui.navigation.MainNavigation
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun App() {
    setSingletonImageLoaderFactory { context ->
        getAsyncImageLoader(context)
    }
    KoinContext {
        MainNavigation()
    }
}

fun getAsyncImageLoader(context: PlatformContext)=
    ImageLoader.Builder(context).crossfade(true).logger(DebugLogger()).build()