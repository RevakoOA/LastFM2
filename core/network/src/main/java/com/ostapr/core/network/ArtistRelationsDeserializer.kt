package com.ostapr.core.network

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.ostapr.core.network.data.ArtistRelations
import com.ostapr.core.network.data.RelationItem
import com.ostapr.core.network.data.RelationItem.Companion.INCLUDED_TYPES
import java.lang.reflect.Type

class ArtistRelationsDeserializer(private val urlConvertor: MusicBrainzUrlConvertor) :
    JsonDeserializer<ArtistRelations> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): ArtistRelations {
        val relationsList = (json as JsonObject).get(RELATIONS).asJsonArray.filter { jsonItem ->
            (jsonItem as JsonObject).get(TYPE).asString in INCLUDED_TYPES
        }.map { jsonItem ->
            jsonItem as JsonObject
            val type = jsonItem.get(TYPE).asString
            val urlObject = jsonItem.get(URL)
            val rawUrl = if (urlObject is JsonObject) {
                // came from server
                urlObject.get(RESOURCE).asString
            } else {
                urlObject.asString
            }
            val url = urlConvertor.convertPageUrlToFileUrl(rawUrl)

            RelationItem(type = type, url = url)
        }

        return ArtistRelations(relationsList)
    }

    private companion object {
        const val RELATIONS = "relations"
        const val TYPE = "type"
        const val URL = "url"
        const val RESOURCE = "resource"
    }
}