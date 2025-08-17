package org.example.infinitinews.ui.navigation

import androidx.core.bundle.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.thauvin.erik.urlencoder.UrlEncoderUtil
import org.example.infinitinews.data.model.Article

object ArticleNavType : NavType<Article>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Article? {
        return Json.decodeFromString(bundle.getString(key)?:return null)
    }

    override fun parseValue(value: String): Article {
        return Json.decodeFromString(UrlEncoderUtil.decode(value))
    }

    override fun put(bundle: Bundle, key: String, value: Article) {
        bundle.putString(key, Json.encodeToString<Article>(value))
    }

    override fun serializeAsValue(value: Article): String = UrlEncoderUtil.encode(Json.encodeToString(value))

}