package org.example.infinitinews.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import net.thauvin.erik.urlencoder.UrlEncoderUtil
import org.example.infinitinews.data.model.Article

@Composable
fun DetailsScreen(article: Article, navigateToBack: (() -> Unit)) {
    Column( modifier = Modifier.fillMaxSize().statusBarsPadding().systemBarsPadding()) {
        Box {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth(),
                model = UrlEncoderUtil.decode(article.urlToImage.orEmpty()),
                contentDescription = null
            )

            IconButton(onClick = { navigateToBack.invoke() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        }
        Text(
            modifier = Modifier.padding(horizontal = 5.dp),
            text = article.title,
            style = TextStyle(
                color = Color.Red,
                textAlign = TextAlign.Justify,
                fontWeight = FontWeight.ExtraBold,
                fontSize = TextUnit(25F, TextUnitType.Sp)
            )
        )
        Text(
            modifier = Modifier.padding(horizontal = 5.dp),
            text = article.description.orEmpty(),
            style = TextStyle(
                color = Color.DarkGray,
                textAlign = TextAlign.Justify,
                fontWeight = FontWeight.SemiBold,
                fontSize = TextUnit(20F, TextUnitType.Sp)
            )
        )
        Text(
            modifier = Modifier.padding(horizontal = 5.dp),
            text = article.content.orEmpty(),
            style = TextStyle(
                color = Color.DarkGray,
                textAlign = TextAlign.Justify,
                fontWeight = FontWeight.SemiBold,
                fontSize = TextUnit(20F, TextUnitType.Sp)
            )
        )
    }
}