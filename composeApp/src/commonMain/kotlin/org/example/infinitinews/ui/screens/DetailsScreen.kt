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

@Composable
fun DetailsScreen(urlToImage: String?, title:String?, description:String?, content:String?, navigateToBack: (() -> Unit)) {
    Column( modifier = Modifier.fillMaxSize().statusBarsPadding().systemBarsPadding()) {
        Box {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth(),
                model = UrlEncoderUtil.decode(urlToImage!!),
                contentDescription = null
            )

            IconButton(onClick = { navigateToBack.invoke() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Localized description"
                )
            }
        }
        Text(
            modifier = Modifier.padding(horizontal = 5.dp),
            text = title?:"",
            style = TextStyle(
                color = Color.Red,
                textAlign = TextAlign.Justify,
                fontWeight = FontWeight.ExtraBold,
                fontSize = TextUnit(25F, TextUnitType.Sp)
            )
        )
        Text(
            modifier = Modifier.padding(horizontal = 5.dp),
            text = description?:"",
            style = TextStyle(
                color = Color.DarkGray,
                textAlign = TextAlign.Justify,
                fontWeight = FontWeight.SemiBold,
                fontSize = TextUnit(20F, TextUnitType.Sp)
            )
        )
        Text(
            modifier = Modifier.padding(horizontal = 5.dp),
            text = content?:"",
            style = TextStyle(
                color = Color.DarkGray,
                textAlign = TextAlign.Justify,
                fontWeight = FontWeight.SemiBold,
                fontSize = TextUnit(20F, TextUnitType.Sp)
            )
        )
    }
}