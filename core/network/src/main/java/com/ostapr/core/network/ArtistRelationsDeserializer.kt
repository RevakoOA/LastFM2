package com.ostapr.core.network

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.ostapr.core.network.data.ArtistRelations
import com.ostapr.core.network.data.RelationItem
import com.ostapr.core.network.data.RelationItem.Companion.INCLUDED_TYPES
import java.lang.reflect.Type

class ArtistRelationsDeserializer : JsonDeserializer<ArtistRelations> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): ArtistRelations {
        val relationsList = (json as JsonObject).get("relations").asJsonArray.filter { jsonItem ->
            (jsonItem as JsonObject).get("type").asString in INCLUDED_TYPES
        }.map { jsonItem ->
            jsonItem as JsonObject
            val type = jsonItem.get("type").asString
            val urlObject = jsonItem.get("url")
            val url = if (urlObject is  JsonObject) {
                // came from server
                urlObject.get("resource").asString
            } else {
                urlObject.asString
            }
            RelationItem(type = type, url = url)
        }

        return ArtistRelations(relationsList)
    }
}
