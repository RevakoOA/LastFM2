package com.ostapr.core.network.musicbrainz

/**
 * We try to search for artist images from MusicBrainz service. For some data services like
 * Wikipedia it returns URL to the page, not the image.
 *
 * This Url convertor convert all such known cases.
 */
class MusicBrainzUrlConvertor(private val convertors: List<UrlConvertor>) : UrlConvertor {

    override fun knowHowToFind(pageUrl: String): Boolean = true

    override suspend fun fetchImageUrls(pageUrl: String): List<String> {
        for(convertor in convertors) {
            if (convertor.knowHowToFind(pageUrl)) {
                return convertor.fetchImageUrls(pageUrl)
            }
        }

        return listOf(pageUrl)
    }
}