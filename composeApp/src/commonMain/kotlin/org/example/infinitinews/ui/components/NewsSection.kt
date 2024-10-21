package org.example.infinitinews.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.infinitinews.data.network.ApiResponse
import org.example.infinitinews.ui.viewmodels.MainViewModel
import kotlinx.coroutines.launch
import org.example.infinitinews.data.model.Article


@Composable
fun BreakingNewsSection(viewModel: MainViewModel, snackbarState: SnackbarHostState, onItemClick: (Article) -> Unit) {
    val viewModelState by viewModel.headlinesState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        when (val uiState = viewModelState) {
            is ApiResponse.Success -> {
                Column  {
                    uiState.data?.let { articles ->
                        Text(text = "Breaking News", modifier = Modifier.padding(start = 20.dp), style = TextStyle(color = Color.Red, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = TextUnit(20F, TextUnitType.Sp)))
                        LazyRow(modifier = Modifier.padding(start = 10.dp)) {
                            items(articles.size) { index ->
                                val article = articles[index]
                                article.urlToImage?.let {
                                    BreakingNewsItem(article = article, onItemClick = onItemClick)
                                }
                            }
                        }
                    }
                }
            }

            is ApiResponse.Error -> {
                LaunchedEffect(uiState.error) {
                    scope.launch {
                        snackbarState.showSnackbar(uiState.error.message.toString())
                    }
                }
            }

            ApiResponse.Loading -> {
                Loader()
            }
        }
    }

}

@Composable
fun AllNewsSection(viewModel: MainViewModel, snackbarState: SnackbarHostState, onItemClick: (Article) -> Unit) {

    val viewModelState by viewModel.allNewsState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        when (val uiState = viewModelState) {
            is ApiResponse.Success -> {
                Column  {
                    uiState.data?.let { articles ->
                        Text(text = "What's happening around the world", modifier = Modifier.padding(start = 20.dp), style = TextStyle(color = Color.Red, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, fontSize = TextUnit(20F, TextUnitType.Sp)))
                        LazyColumn(modifier = Modifier.padding(start = 10.dp)) {
                            items(articles.size) { index ->
                                val article = articles[index]
                                article.urlToImage?.let {
                                    AllNewsItem(article = article, onItemClick = onItemClick)
                                }
                            }
                        }
                    }
                }
            }

            is ApiResponse.Error -> {
                LaunchedEffect(uiState.error) {
                    scope.launch {
                        snackbarState.showSnackbar(uiState.error.message.toString())
                    }
                }
            }

            ApiResponse.Loading -> {
                Loader()
            }
        }
    }
}