package com.ostapr.core.network

/**
 * We try to search for artist images from MusicBrainz service. For some data services like
 * Wikipedia it returns URL to the page, not the image.
 *
 * This Url convertor convert all such known cases.
 */
class MusicBrainzUrlConvertor {

    /** Convert page url to image url for known issues data services. */
    fun convertPageUrlToFileUrl(url: String): String = when {
        url.contains(WIKI_COMMON_URL) -> {
            val fileName = url.substringAfterLast(WIKI_FILE_URL_SUBPART)
            WIKI_UPLOAD_URL + fileName
        }
        else -> url
    }

    companion object {
        const val WIKI_FILE_URL_SUBPART = "File:"
        const val WIKI_COMMON_URL = "https://commons.wikimedia.org"
        const val WIKI_UPLOAD_URL = "https://upload.wikimedia.org/wikipedia/commons/f/ff/"
    }
}