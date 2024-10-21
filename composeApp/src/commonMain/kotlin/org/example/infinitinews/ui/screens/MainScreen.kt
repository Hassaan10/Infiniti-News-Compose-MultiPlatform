package org.example.infinitinews.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.infinitinews.ui.components.AllNewsSection
import org.example.infinitinews.ui.components.BreakingNewsSection
import org.example.infinitinews.ui.events.UIEvent
import org.example.infinitinews.data.model.Article
import org.example.infinitinews.ui.viewmodels.MainViewModel
import org.koin.compose.viewmodel.koinNavViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(ExperimentalMaterialApi::class, KoinExperimentalAPI::class)
@Composable
fun MainScreen(viewModel: MainViewModel = koinNavViewModel<MainViewModel>(), onItemClick: (Article) -> Unit) {
    val snackbarState = remember { SnackbarHostState() }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = false,
        onRefresh = { viewModel.sendUIEvents(UIEvent.FetchEverything) }
    )
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(backgroundColor = Color.White, title = {
        Column {
            Text(text = "Hello")
            Text(text = "Explore the news around the world ")
        }
    })
    }) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            SnackbarHost(hostState = snackbarState)
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.pullRefresh(
                state = pullRefreshState,

            )) {
                Spacer(Modifier.height(10.dp))
                BreakingNewsSection(viewModel, snackbarState, onItemClick = onItemClick)
                Spacer(Modifier.height(20.dp))
                AllNewsSection(viewModel, snackbarState, onItemClick = onItemClick)
            }
        }
    }
}